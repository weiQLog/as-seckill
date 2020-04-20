package com.as.seckill.scheduled;

import com.as.seckill.service.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时操作缓存： 清理等操作
 */
@Component
@Configurable
@Slf4j
public class CacheManagement {

    @Resource
    private RedisCacheService redisCacheService;

    // 每天两点清除缓存
    @Scheduled(cron = "0 0 2 * * ?")
    public void clearSeckillCache() {
        redisCacheService.clearSeckillCache();
        log.info("清理缓存");
    }

    // 每五分钟扫描一遍最近12小时内的秒杀消息加入到缓存中
    @Scheduled(cron = "0 0/1 * * * ?")
    public void addSeckillCache(){
        log.info("execute CacheManagement.addSeckillCache");
        redisCacheService.addSeckillCache(null);
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void disposeDelayMessage(){
        log.info("处置未及时支付的订单");
        redisCacheService.disposeDelayMessage();
    }


}
