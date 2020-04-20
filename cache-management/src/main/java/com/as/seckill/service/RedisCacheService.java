package com.as.seckill.service;

import com.as.seckill.Goods;
import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;

import java.util.List;

public interface RedisCacheService {

    /**
     * 添加秒杀信息的缓存
     * @param seckill 商品的秒杀信息
     */
    public void addSeckillCache(Seckill seckill);

    /**
     * 清理缓存
     */
    public void clearSeckillCache();

    /**
     * 将秒杀成功信息添加到延时队列中，10分钟后为订单未支付 订单设置为无效
     * @param seckilled 秒杀成功明细
     */
    public void addSeckilled(Seckilled seckilled);

    /**
     * 获取缓存中的所有秒杀信息
     */
    public List<Seckill> acquire();

    /**
     * 减少缓存中的秒杀库存数量
     */
    public int decreaseSeckillStock(String key, Integer number);

    /**
     * 增加库存
     */
    public int increaseSeckillStock(String key, Integer number);

    /**
     * 处置延时队列中未及时支付的订单，如果没有，则设置失效
     */
    public void disposeDelayMessage();

    public Goods getGoodsByKey(String key);

    public String get(String key);
}
