package com.as.seckill.service.impl;

import com.as.seckill.Goods;
import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.service.GoodService;
import com.as.seckill.service.RedisCacheService;
import com.as.seckill.service.SeckillService;
import com.as.seckill.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 缓存服务
 * 将秒杀信息添加到缓存中
 * 清理秒杀完成的缓存
 */
@Service
@Slf4j
public class redisCacheServiceImpl implements RedisCacheService {

    /**
     * 加载{@code loadRange}笑死内的发生的秒杀到缓存
     */
    @Value("${redisCache.loadRange}")
    private Long loadRange = 0L;

    @Value("${redisCache.paymentTimeliness}")
    private Integer paymentTimeliness = 0;

    /**
     * redis 客户端
     */
    @Resource
    private RedisTemplate<String, Object> redisCacheTemple;

    /**
     * 商品服务调用
     */
    @Resource
    private GoodService goodService;

    /**
     * 秒杀服务调用
     */
    @Resource
    private SeckillService seckillService;

    /**
     * 添加{@code seckill}到缓存中， key为 "seckill:id:goodsId"
     *
     * @param seckill 商品的秒杀信息
     */
    @Override
    public void addSeckillCache(Seckill seckill) {
        ValueOperations<String, Object> valueOperations = redisCacheTemple.opsForValue();
        if (seckill == null) { // 如果参数为空， 则将特定时间范围内的秒杀加入到缓存中
            long current = System.currentTimeMillis();
            String currentTime = DateUtils.format(current);
            String untilTime = DateUtils.format(current + loadRange);
            CommonResult<List<Seckill>> result = seckillService.acquireByStartTime(currentTime, untilTime);
            List<Seckill> list = result.getData();
            if (list.size() != 0) {
                list.forEach(item -> {
                    String cacheKey = "seckill:" + item.getId() + ":" + item.getGoodsId();
                    item.getGoods().setVersion(null);
                    item.setVersion(null);
                    valueOperations.set(cacheKey, item);
                });
            }
        } else {
            String cacheKey = "seckill:" + seckill.getId() + ":" + seckill.getGoodsId();
            valueOperations.set(cacheKey, seckill);
        }
    }

    /**
     * 清理缓存
     */
    @Override
    public void clearSeckillCache() {
        Set<String> keys = redisCacheTemple.keys("seckill:*");
        if (keys != null) {
            custom(keys, seckill -> {
                long endTime = DateUtils.timestamp(seckill.getSeckillEndTime());
                long currentTime = System.currentTimeMillis();
                if (endTime < currentTime) {  // 秒杀已结束 清楚缓存
                    if (seckill.getSeckillStock() != 0) { // 如果有剩余缓存
                        goodService.increaseGoodsStock(seckill.getGoodsId(), seckill.getSeckillStock());
                        log.info("剩余缓存， 加载回商品库存");
                    }
                    redisCacheTemple.delete("seckill:" + seckill.getId() + ":" + seckill.getGoodsId());
                    log.info("已经清理了");
                }
            });
        }
    }

    /**
     * 将秒杀成功信息添加到延时队列中，10分钟后为订单未支付 订单设置为无效
     *
     * @param seckilled 秒杀成功明细
     */
    @Override
    public void addSeckilled(Seckilled seckilled) {
        ZSetOperations<String, Object> zSetOperations = redisCacheTemple.opsForZSet();
        long creationTime = DateUtils.timestamp(seckilled.getSeckilledCreationTime());
        String key = "seckilledBlockingQueue";
        zSetOperations.add(key, seckilled, creationTime + paymentTimeliness);// 设置为二十分钟之后的时间
    }

    @Override
    public List<Seckill> acquire() {
        Set<String> keys = redisCacheTemple.keys("seckill:*");
        List<Seckill> list = new LinkedList<>();
        if (keys == null) {
            return null;
        } else {
            custom(keys, list::add);
        }
        return list;
    }

    /**
     * 减少缓存中的秒杀库存数量
     */
    @Override
    public synchronized int decreaseSeckillStock(String key, Integer number) {
        ValueOperations<String, Object> valueOperations = redisCacheTemple.opsForValue();
        Seckill seckill = (Seckill) valueOperations.get(key);
        if (seckill == null) { // 缓存不存在，秒杀已经结束
            return -1;
        } else if (seckill.getSeckillStock() == 0) {  // 如果没了库存
            return 0;
        }
        seckill.setSeckillStock(seckill.getSeckillStock() - number);
        valueOperations.set(key, seckill);
        return 1;
    }

    /**
     * 增加库存
     */
    @Override
    public synchronized int increaseSeckillStock(String key, Integer number) {
        ValueOperations<String, Object> valueOperations = redisCacheTemple.opsForValue();
        Seckill seckill = (Seckill) valueOperations.get(key);
        if (seckill == null) {
            return -1;
        }
        seckill.setSeckillStock(seckill.getSeckillStock() + number);
        valueOperations.set(key, seckill);
        return 1;
    }

    @Override
    public void disposeDelayMessage() {
        ZSetOperations<String, Object> zSetOperations =  redisCacheTemple.opsForZSet();
        // 获取队列中第一个元素
        Set<Object> seckilleds = zSetOperations.range("seckilledBlockingQueue", 0 , 0);
        assert  seckilleds != null;
        seckilleds.forEach(obj -> {
            Double score = zSetOperations.score("seckilledBlockingQueue",obj);
            assert  score != null;
            long time = score.longValue();
            if(time <= System.currentTimeMillis()){
                zSetOperations.remove("seckilledBlockingQueue", obj);
                Seckilled seckilled  = (Seckilled)obj;
                seckillService.setState(seckilled.getId(), seckilled.getUserPhone(), -1);
                increaseSeckillStock("seckill:"+seckilled.getId()+":"+seckilled.getGoodsId(), seckilled.getSeckilledNumber());
            }
        });
    }

    /**
     * 从缓存中拿商品信息
     */
    @Override
    public Goods getGoodsByKey(String key) {
        ValueOperations<String, Object> valueOperations = redisCacheTemple.opsForValue();
        Goods goods = (Goods) valueOperations.get(key);
        return goods;
    }

    @Override
    public String get(String key) {
        ValueOperations<String, Object> valueOperations = redisCacheTemple.opsForValue();
        return (String) valueOperations.get(key);
    }

    public void custom(Set<String> keys, Consumer<Seckill> consumer) {
        keys.forEach(key -> {
            ValueOperations<String, Object> valueOperations = redisCacheTemple.opsForValue();
            Seckill seckill = (Seckill) valueOperations.get(key);
            if (seckill != null) {
                consumer.accept(seckill);
            }
        });
    }
}
