package com.as.seckill.dao;

import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeckillMapper {
    // 创建秒杀
    void insert(Seckill seckill);

    // 修改秒杀信息
    void update(Seckill seckill);

    // 查询指定startTime1 - startTime2 时间之内的秒杀信息
    List<Seckill> selectByStartTime(@Param("startTime1") String startTime1,
                                    @Param("startTime2") String startTime2);

    /**
     * 查询秒杀结束时间在 {@code endTime1} 和 {@code endTime2} 之间的秒杀信息
     */
    List<Seckill> selectByEndTime(@Param("endTime1") String endTime1,
                                  @Param("endTime2") String endTime2);
    // 根据id查询秒杀信息
    Seckill selectById(@Param("id") Long id);

    /**
     * 创建秒杀订单
     */
    void insertSeckilled(Seckilled seckilled);

    /**
     * 根据手机号查询订单
     */
    List<Seckilled> selectSeckilledByPhone(@Param("userPhone") Long userPhone);

    Seckilled selectSeckilledPrimaryKey(@Param("id") Long id,
                                        @Param("userPhone") Long userPhone);

    void updateState(@Param("id") Long id,
                     @Param("userPhone") Long userPhone,
                     @Param("state") Integer state);

    List<Seckilled> selectSeckilledByPrimaryKey(@Param("idList") List<Long> idList,@Param("userPhone") Long userPhone);

}
