package com.as.seckill.service.fallback;

import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.result.ServerErrorCommonResult;
import com.as.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SeckillServiceFallback implements SeckillService {
    @Override
    public CommonResult<Object> createSeckill(Seckill seckill) {
        log.info("来自SeckillServiceFallback.createSeckill");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<List<Seckill>> acquireByStartTime(String startTime1, String startTime2) {
        log.info("来自SeckillServiceFallback.acquireByStartTime");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<List<Seckill>> acquireByEndTime(String endTime1, String endTime2) {
        log.info("来自SeckillServiceFallback.acquireByEndTime");
        return new ServerErrorCommonResult<>();
    }

    /**
     * 获取所有秒杀信息
     */
    @Override
    public CommonResult<List<Seckill>> acquire() {
        log.info("来自SeckillServiceFallback.acquire");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<Object> createSeckilled(Seckilled seckilled) {
        log.info("来自SeckillServiceFallback.createSeckilled");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<List<Seckilled>> acquireSeckilledByPhone(Long userPhone) {
        log.info("来自SeckillServiceFallback.acquireSeckilledByPhone");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<List<Seckilled>> acquireSeckilledByPrimaryKey(List<Seckilled> SeckilledList) {
        log.info("来自SeckillServiceFallback.acquireSeckilledByPrimaryKey");
        return new ServerErrorCommonResult<>();
    }
}
