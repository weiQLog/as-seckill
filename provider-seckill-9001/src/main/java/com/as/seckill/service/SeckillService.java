package com.as.seckill.service;

import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;

import java.util.List;

public interface SeckillService {
    /**
     * 创建商品秒杀
     * @param seckill 要添加的秒杀信息
     */
    void createSeckill(Seckill seckill);

    /**
     * 修改秒杀信息
     * @param seckill 要修改的秒杀信息
     */
    void modifySeckill(Seckill seckill);

    /**
     * 获取秒杀开始时间在{@code time1} 和 {@code time2} 之间的秒杀
     */
    List<Seckill> acquireByStartTime(String time1, String time2);

    /**
     * 获取秒杀开始时间在{@code time1} 和 {@code time2} 之间的秒杀
     */
    List<Seckill> acquireByEndTime(String time1, String time2);

    /**
     * 根据{@code id} 获取秒杀信息
     */
    Seckill acquireById(Long id);

    void addToCache(Seckill seckill);

    void createSeckilled(Seckilled seckilled);

    /**
     * 根据手机号获取订单
     */
    List<Seckilled> acquireSeckilledByPhone(Long userPhone);

    /**
     * 根据联合主键获取订单
     */
    Seckilled acquireSeckilledPrimaryKey(Long id, Long userPhone);

    /**
     * 设置订单状态
     */
    void setState(Long id, Long userPhone, Integer state);

    /**
     * 获取订单信息
     */
    List<Seckilled> acquireSeckilledByPrimaryKey(List<Long> idList, Long userPhone);
}
