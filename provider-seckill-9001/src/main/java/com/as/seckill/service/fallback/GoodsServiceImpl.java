package com.as.seckill.service.fallback;

import com.as.seckill.result.CommonResult;
import com.as.seckill.service.GoodsService;
import org.springframework.stereotype.Component;

@Component
public class GoodsServiceImpl implements GoodsService {

    /**
     * GoodsService的降级方法
     */
    @Override
    public CommonResult<Object> decreaseGoodsStock(Long id, Integer number) {
        return new CommonResult<>(500, "服务器错误o(╥﹏╥)o，请稍后再试~");
    }
}
