package com.as.seckill.service;

import com.as.seckill.Seckill;
import com.as.seckill.result.CommonResult;
import com.as.seckill.service.fallback.SeckillServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "provider-seckill", fallback = SeckillServiceFallback.class)
public interface SeckillService {

    @GetMapping("/seckillService/acquireByStartTime")
    public CommonResult<List<Seckill>> acquireByStartTime(@RequestParam("startTime1") String startTime1,
                                                          @RequestParam("startTime2") String startTime2);

    @GetMapping("/seckillService/acquireById")
    public CommonResult<Seckill> acquireById(@RequestParam("id") Long id);


    @PostMapping("/seckillService/setState")
    public CommonResult<Object> setState(@RequestParam("id") Long id,
                                         @RequestParam("userPhone") Long userPhone,
                                         @RequestParam("state") Integer state);
}