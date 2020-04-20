package com.as.seckill.service;

import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.service.fallback.SeckillServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "provider-seckill", fallback = SeckillServiceFallback.class)
public interface SeckillService {

    @PostMapping("/seckillService/createSeckill")
    CommonResult<Object> createSeckill(@SpringQueryMap Seckill seckill);

    @GetMapping("/seckillService/acquireByStartTime")
    public CommonResult<List<Seckill>> acquireByStartTime(@RequestParam("startTime1") String startTime1,
                                                          @RequestParam("startTime2") String startTime2);

    @GetMapping("/seckillService/acquireByEndTime")
    public CommonResult<List<Seckill>> acquireByEndTime(@RequestParam("endTime1") String endTime1,
                                                        @RequestParam("endTime2") String endTime2);

    /**
     * 获取所有秒杀信息
     */
    @GetMapping("/seckillService/acquire")
    public CommonResult<List<Seckill>> acquire();

    @PostMapping("/seckillService/createSeckilled")
    public CommonResult<Object> createSeckilled(@SpringQueryMap Seckilled seckilled);

    @GetMapping("/seckillService/acquireSeckilledByPhone")
    public CommonResult<List<Seckilled>> acquireSeckilledByPhone(@RequestParam("userPhone") Long userPhone);

    @GetMapping("/seckillService/acquireSeckilledByPrimaryKey")
    public CommonResult<List<Seckilled>> acquireSeckilledByPrimaryKey(List<Seckilled> SeckilledList);
}
