package com.as.seckill.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.as.seckill.Seckill;
import com.as.seckill.Seckilled;
import com.as.seckill.controller.limit.BlockHandler;
import com.as.seckill.result.CommonResult;
import com.as.seckill.result.SucceedCommonResult;
import com.as.seckill.service.SeckillService;
import com.as.seckill.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequestMapping("/seckill")
@RestController
@Slf4j
public class ConsumerSeckillController {

    @Resource
    private SeckillService seckillService;

    @PostMapping("/createSeckill")
    public CommonResult<Object> createSeckill(@RequestBody Map<String, Seckill> map) {
        Seckill seckill = map.get("seckill");
        log.info("POST /seckill/createSeckill ");
        return seckillService.createSeckill(seckill);
    }

    @GetMapping("/acquireByStartTime")
    public CommonResult<List<Seckill>> acquireByStartTime(@RequestParam String startTime1,
                                                          @RequestParam String startTime2) {
        CommonResult<List<Seckill>> result = seckillService.acquireByStartTime(startTime1, startTime2);
        log.info("GET /seckill/acquireByStartTime ");
        return new SucceedCommonResult<>(result.getData());
    }

    @GetMapping("/acquireByEndTime")
    public CommonResult<List<Seckill>> acquireByEndTime(@RequestParam(value = "endTime1", required = false) String endTime1,
                                                        @RequestParam(value = "endTime2", required = false) String endTime2) {
        long current = System.currentTimeMillis();
        if (endTime1 == null) {
            endTime1 = DateUtils.format(current);
        } else if (endTime2 == null) {
            // 12 小时内
            endTime2 = DateUtils.format(current + 43200000);
        }
        log.info("GET /seckill/acquireByEndTime ");
        return seckillService.acquireByEndTime(endTime1, endTime2);
    }

    @GetMapping("/acquire")
    public CommonResult<List<Seckill>> acquire() {
        log.info("GET /seckill/acquire ");
        return seckillService.acquire();
    }

    /**
     * 创建秒杀订单
     */
    @PostMapping("/createSeckilled")
    @SentinelResource(value = "createSeckilled", blockHandlerClass = BlockHandler.class, blockHandler = "createSeckilled")
    public CommonResult<Object> createSeckilled(@RequestBody Map<String, Seckilled> map) {
        Seckilled seckilled = map.get("seckilled");
        seckilled.setUserPhone(random(11));
        seckilled.setSeckilledCreationTime(null);
        seckilled.setSeckilledState(0);
        return seckillService.createSeckilled(seckilled);
    }

    private  Long random(int length) {
        Random rand = new Random();
        String cardNumber = "";
        for (int i = 0; i < length; i++) {
            cardNumber += rand.nextInt(10);
        }
        return Long.valueOf(cardNumber);
    }

    /**
     * 根据手机号查询订单
     *
     * @param userPhone 手机号
     */
    @GetMapping("/acquireSeckilledByPhone")
    public CommonResult<List<Seckilled>> acquireSeckilledByPhone(@RequestParam Long userPhone) {
        return seckillService.acquireSeckilledByPhone(userPhone);
    }

    @GetMapping("/acquireSeckilledByPrimaryKey")
    public CommonResult<List<Seckilled>> acquireSeckilledByPrimaryKey(List<Seckilled> seckilledList) {
        return seckillService.acquireSeckilledByPrimaryKey(seckilledList);
    }

}
