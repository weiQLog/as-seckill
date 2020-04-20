package com.as.seckill.service.fallback;

import com.as.seckill.result.CommonResult;
import com.as.seckill.result.ServerErrorCommonResult;
import com.as.seckill.service.GoodService;
import org.springframework.stereotype.Component;

@Component
public class GoodsServiceFallback implements GoodService {
    @Override
    public CommonResult<Object> increaseGoodsStock(Long id, Integer number) {
        return new ServerErrorCommonResult<>();
    }
}
