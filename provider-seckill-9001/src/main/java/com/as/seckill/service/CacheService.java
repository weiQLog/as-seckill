package com.as.seckill.service;

import com.as.seckill.Goods;
import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.service.fallback.CacheServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "cache-management", fallback = CacheServiceFallback.class)
public interface CacheService {

    /**
     * 将指定id的秒杀信息加载到缓存
     */
    @PostMapping("/redisCache/SeckillCache")
    public CommonResult<Seckill> addSeckillCache(@RequestParam("id") Long id);

    /**
     * 将秒杀成功的明细加载到延时队列
     */
    @PostMapping("/redisCache/SeckilledCache")
    public CommonResult<Object> addSeckilled(@SpringQueryMap Seckilled seckilled);

    /**
     * 获取所有秒杀信息
     */
    @GetMapping("/redisCache/acquireSeckill")
    public CommonResult<List<Seckill>> acquire();

    @PostMapping("/redisCache/decreaseSeckillStock")
    public CommonResult<Object> decreaseSeckillStock(@RequestParam("key") String key,
                                                     @RequestParam("number") Integer number);

    @PostMapping("/redisCache/increaseSeckillStock")
    public CommonResult<Object> increaseSeckillStock(@RequestParam("key") String key,
                                                      @RequestParam("number") Integer number);

    @GetMapping("/redisCache/getGoodsByKey")
    public CommonResult<Goods> getGoodsByKey(@RequestParam("key") String key);
}
