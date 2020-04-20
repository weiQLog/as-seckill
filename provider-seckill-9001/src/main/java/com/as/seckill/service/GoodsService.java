package com.as.seckill.service;

import com.as.seckill.result.CommonResult;
import com.as.seckill.service.fallback.GoodsServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "provider-goods", fallback = GoodsServiceImpl.class)
public interface GoodsService {

    /**
     * 创建秒杀, 预减商品库存
     * @return 返回执行结果
     */
    @PostMapping(value = "/goodsService/modifyGoodsStock/decrease")
    CommonResult<Object> decreaseGoodsStock(@RequestParam("id") Long id,
                                            @RequestParam("number") Integer number);
}