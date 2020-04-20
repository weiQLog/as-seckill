package com.as.seckill.service;

import com.as.seckill.result.CommonResult;
import com.as.seckill.service.fallback.GoodsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "provider-goods", fallback = GoodsServiceFallback.class)
public interface GoodService {
    @PostMapping(value = "/goodsService/modifyGoodsStock/increase")
    CommonResult<Object> increaseGoodsStock(@RequestParam("id") Long id,
                                            @RequestParam("number") Integer number);
}
