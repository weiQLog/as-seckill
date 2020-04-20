package com.as.seckill.scheduled;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class CreateCache {

    /**
     * 将今天和明天的秒杀加入到缓存
     */
    @Scheduled(cron = "")
    public void createSeckillCache(){

    }
}
