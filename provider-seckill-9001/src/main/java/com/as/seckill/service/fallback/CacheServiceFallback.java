package com.as.seckill.service.fallback;

import com.as.seckill.Goods;
import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.result.ServerErrorCommonResult;
import com.as.seckill.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CacheServiceFallback implements CacheService {
    /**
     * 将指定id的秒杀信息加载到缓存
     */
    @Override
    public CommonResult<Seckill> addSeckillCache(Long id) {
        log.info("CacheServiceFallback.addSeckillCache");
        return new ServerErrorCommonResult<>();
    }

    /**
     * 将秒杀成功的明细加载到延时队列
     */
    @Override
    public CommonResult<Object> addSeckilled(Seckilled seckilled) {
        log.info("CacheServiceFallback.addSeckilled");
        return new ServerErrorCommonResult<>();
    }

    /**
     * 获取所有秒杀信息
     */
    @Override
    public CommonResult<List<Seckill>> acquire() {
        log.info("CacheServiceFallback.acquire");
        return new ServerErrorCommonResult<>();
    }

    /**
     * 减少秒杀库存
     */
    @Override
    public CommonResult<Object> decreaseSeckillStock(String key, Integer number) {
        log.info("CacheServiceFallback.decreaseSeckillStock");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<Object> increaseSeckillStock(String key, Integer number) {
        log.info("CacheServiceFallback.increaseSeckillStock");
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<Goods> getGoodsByKey(String key) {
        return null;
    }
}
