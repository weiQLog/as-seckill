package com.as.seckill;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisCacheTemple;

    @Test
    public void get() throws ParseException {
//        stringRedisTemplate.opsForValue().set("username", "");
//        Seckill seckill =new Seckill(1L, 100, new Timestamp(System.currentTimeMillis()),
//                new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()), new BigDecimal("999"),new Goods(),0);
//        redisCacheTemple.opsForValue().set(seckill.getId()+":还好",seckill );
//        final Seckill k1 = (Seckill) redisCacheTemple.opsForValue().get(seckill.getId()+":还好");
//        log.info("[字符缓存结果] - [{}]", k1.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "2020-04-15 00:00:00";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);//-1.昨天时间 0.当前时间 1.明天时间 *以此类推
        Date date = sdf.parse(time);
        System.out.println(date.getTime());
//        String time = sdf.format(c.getTime());
        System.out.println("时间是：" + sdf.format(date.getTime()));
    }
}
