package com.as.seckill.service.fallback;

import com.as.seckill.Seckill;
import com.as.seckill.result.CommonResult;
import com.as.seckill.result.ServerErrorCommonResult;
import com.as.seckill.service.SeckillService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeckillServiceFallback implements SeckillService {
    @Override
    public CommonResult<List<Seckill>> acquireByStartTime(String startTime1, String startTime2) {
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<Seckill> acquireById(Long id) {
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<Object> setState(Long id, Long userPhone, Integer state) {
        return new ServerErrorCommonResult<>();
    }
}
