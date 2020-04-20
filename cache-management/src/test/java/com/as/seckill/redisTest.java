package com.as.seckill;

import com.as.seckill.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class redisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisCacheTemple;

    @Test
    public void test(){
//        Seckilled seckilled1 = Seckilled.builder().id(5L).seckillCreationTime("2015-01-01 12:12:12").build();
//        Seckilled seckilled2 = Seckilled.builder().id(5L).seckillCreationTime("2015-01-02 12:12:12").build();
//        Seckilled seckilled3 = Seckilled.builder().id(5L).seckillCreationTime("2014-01-01 12:12:12").build();
//        ZSetOperations<String, Object> zSetOperations =  redisCacheTemple.opsForZSet();
//        long creationTime1 = DateUtils.timestamp(seckilled1.getSeckillCreationTime());
//        long creationTime2 = DateUtils.timestamp(seckilled2.getSeckillCreationTime());
//        long creationTime3 = DateUtils.timestamp(seckilled3.getSeckillCreationTime());
//        zSetOperations.add("seckilledBlockingQueue", seckilled1, creationTime1);
//        zSetOperations.add("seckilledBlockingQueue", seckilled2, creationTime2);
//        zSetOperations.add("seckilledBlockingQueue", seckilled3, creationTime3);
//        Set<Object> seckilleds = zSetOperations.range("seckilledBlockingQueue", 0 , 0);
//        seckilleds.forEach(seckilled -> {
//            System.out.println(((Seckilled)seckilled).getSeckillCreationTime());
//            }
//        );

//        System.out.println(1587318856258L > 1587318764010L);

        ZSetOperations<String, Object> zSetOperations =  redisCacheTemple.opsForZSet();
        // 获取队列中第一个元素
        Set<Object> seckilleds = zSetOperations.range("seckilledBlockingQueue", 0 , 0);
        assert  seckilleds != null;
        log.info(""+seckilleds.size());
        seckilleds.forEach(seckilled -> {
            Double score = zSetOperations.score("seckilledBlockingQueue",seckilled);
            assert  score != null;
            long time = score.longValue();
            log.info("[{}]",time);
            if(time <= System.currentTimeMillis()){
                zSetOperations.remove("seckilledBlockingQueue", seckilled);
            }
            System.out.println(((Seckilled)seckilled).getSeckilledCreationTime());
        });
    }

    @Value("${redisCache.paymentTimeliness}")
    private Integer paymentTimeliness = 0;


    @Test
    public void test2() throws ParseException {
        long b = System.currentTimeMillis();
        long a = DateUtils.add(DateUtils.format(b), Calendar.MINUTE, 1);
        System.out.println("a:" + a);
        System.out.println("b:" + paymentTimeliness);
    }
}
