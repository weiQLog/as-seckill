package com.as.seckill.controller;

import com.as.seckill.Goods;
import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.result.SucceedCommonResult;
import com.as.seckill.service.RedisCacheService;
import com.as.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/redisCache")
public class CacheController {

    @Resource
    private SeckillService seckillService;

    @Resource
    private RedisCacheService redisCacheService;

    /**
     * 将指定id的秒杀信息加入到缓存中
     */
    @PostMapping("/seckillCache")
    public CommonResult<Seckill> addSeckillCache(@RequestParam Long id){
        CommonResult<Seckill> result = seckillService.acquireById(id);
        Seckill seckill = null;
        if(result.getCode().equals(200)){
            seckill = result.getData();
            redisCacheService.addSeckillCache(seckill);
        }
        return result;
    }

    /**
     * 添加到延时队列 ,
     * @param seckilled 秒杀成功明细
     */
    @PostMapping("/seckilledCache")
    public CommonResult<Object> addSeckilled(Seckilled seckilled){
        log.info("[{}]", seckilled);
        redisCacheService.addSeckilled(seckilled);  // 添加到队列
        return new SucceedCommonResult<>();
    }

    /**
     * 从缓存中获取秒杀信息
     */
    @GetMapping("/acquireSeckill")
    public CommonResult<List<Seckill>> acquire(){
        return new  SucceedCommonResult<>(redisCacheService.acquire());
    }

    @PostMapping("/decreaseSeckillStock")
    public CommonResult<Integer> decreaseSeckillStock(@RequestParam String key,
                                                     @RequestParam Integer number){
        int semaphore = redisCacheService.decreaseSeckillStock(key, number);
        return new SucceedCommonResult<>(semaphore);
    }

    @PostMapping("/increaseSeckillStock")
    public CommonResult<Integer> increaseSeckillStock(@RequestParam String key,
                                                      @RequestParam Integer number){
        int semaphore = redisCacheService.increaseSeckillStock(key, number);
        return new SucceedCommonResult<>(semaphore);
    }

    @GetMapping("/getGoodsByKey")
    public CommonResult<Goods> getGoodsByKey(@RequestParam String key){
        Goods goods = redisCacheService.getGoodsByKey(key);
        return new SucceedCommonResult<>(goods);
    }
}