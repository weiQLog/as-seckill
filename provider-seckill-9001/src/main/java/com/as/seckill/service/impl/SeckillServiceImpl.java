package com.as.seckill.service.impl;

import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.dao.SeckillMapper;
import com.as.seckill.service.GoodsService;
import com.as.seckill.service.SeckillService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private SeckillMapper seckillMapper;

    @Resource
    private GoodsService goodsService;

    /**
     * 创建商品秒杀
     * @param seckill 要添加的秒杀信息
     */
    @Override
    @GlobalTransactional(name = "create-Seckill", rollbackFor = Exception.class)
    public void createSeckill(Seckill seckill) {
        // 1： 减少库存
        goodsService.decreaseGoodsStock(seckill.getGoodsId(), seckill.getSeckillStock());
        // 2： 创建秒杀
        seckillMapper.insert(seckill);
        // 检查秒杀开始时间，是否要加入缓存

    }

    /**
     * 修改秒杀信息
     *
     * @param seckill 要修改的秒杀信息
     */
    @Override
    public void modifySeckill(Seckill seckill) {
        seckillMapper.update(seckill);
    }

    /**
     * 查询 time1 - time2 之间开始秒杀的信息
     * @param time1 区间开始
     * @param time2 区间结束
     * @return 返回符合要求的秒杀信息
     */
    @Override
    public List<Seckill> acquireByStartTime(String time1, String time2) {
        return seckillMapper.selectByStartTime(time1, time2);
    }

    @Override
    public List<Seckill> acquireByEndTime(String time1, String time2) {
        return seckillMapper.selectByEndTime(time1, time2);
    }

    @Override
    public Seckill acquireById(Long id) {
        return seckillMapper.selectById(id);
    }

    @Override
    public void addToCache(Seckill seckill) {

    }

    @Override
    public void createSeckilled(Seckilled seckilled) {
        seckillMapper.insertSeckilled(seckilled);
    }

    @Override
    public List<Seckilled> acquireSeckilledByPhone(Long userPhone) {
        return seckillMapper.selectSeckilledByPhone(userPhone);
    }

    @Override
    public Seckilled acquireSeckilledPrimaryKey(Long id, Long userPhone) {
        return seckillMapper.selectSeckilledPrimaryKey(id, userPhone);
    }

    @Override
    public void setState(Long id, Long userPhone, Integer state) {
        seckillMapper.updateState(id, userPhone, state);
    }

    @Override
    public List<Seckilled> acquireSeckilledByPrimaryKey(List<Long> idList, Long userPhone) {
        return seckillMapper.selectSeckilledByPrimaryKey(idList, userPhone);
    }


}
