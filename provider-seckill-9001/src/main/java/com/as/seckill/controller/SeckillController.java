package com.as.seckill.controller;

import com.as.seckill.Goods;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;
import com.as.seckill.Seckill;
import com.as.seckill.result.ServerErrorCommonResult;
import com.as.seckill.result.SucceedCommonResult;
import com.as.seckill.service.CacheService;
import com.as.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/seckillService")
@RestController
@Slf4j
public class SeckillController {

    @Resource
    private SeckillService seckillService;

    @Resource
    private CacheService cacheService;


    @PostMapping("/createSeckill")
    public CommonResult<Object> createSeckill(Seckill seckill) {
        seckillService.createSeckill(seckill);
        return new SucceedCommonResult<>();
    }

    @GetMapping("/acquireByStartTime")
    public CommonResult<List<Seckill>> acquireByStartTime(@RequestParam String startTime1,
                                                          @RequestParam String startTime2) {
        List<Seckill> list = seckillService.acquireByStartTime(startTime1, startTime2);
        return new SucceedCommonResult<>(list);
    }

    @GetMapping("/acquireByEndTime")
    public CommonResult<List<Seckill>> acquireByEndTime(@RequestParam String endTime1,
                                                        @RequestParam String endTime2) {
        List<Seckill> list = seckillService.acquireByEndTime(endTime1, endTime2);
        return new SucceedCommonResult<>(list);
    }

    @GetMapping("/acquireById")
    public CommonResult<Seckill> acquireById(@RequestParam Long id) {
        Seckill seckill = seckillService.acquireById(id);
        return new SucceedCommonResult<>(seckill);
    }

    @GetMapping("/acquire")
    public CommonResult<List<Seckill>> acquire() {
        return cacheService.acquire();
    }

    /**
     * 创建秒杀订单
     */
    @PostMapping("/createSeckilled")
    public CommonResult<Object> createSeckilled(Seckilled seckilled) {
        String seckillKey = "seckill:" + seckilled.getId() + ":" + seckilled.getGoodsId();
        CommonResult<Object> result = cacheService.decreaseSeckillStock(seckillKey, seckilled.getSeckilledNumber());
        if (result.getCode() == 500) {
            return result;
        } else if ((int)result.getData() == -1) {
            return new CommonResult<>(444, "秒杀已经结束");
        } else if ((int)result.getData() == 0) {
            return new CommonResult<>(444, "商品已经被抢光了");
        } else {
            try {
                seckillService.createSeckilled(seckilled);  // 如果这一步出错，反向补偿库存
                Seckilled seckilledCache = seckillService.acquireSeckilledPrimaryKey(seckilled.getId(), seckilled.getUserPhone());
                cacheService.addSeckilled(seckilledCache);// 添加到延时队列
            } catch (Exception e) {
                e.printStackTrace();
                cacheService.increaseSeckillStock(seckillKey, seckilled.getSeckilledNumber());
                return new ServerErrorCommonResult<>();
            }
        }
        return new SucceedCommonResult<>();
    }

    @PostMapping("/setState")
    public CommonResult<Object> setState(@RequestParam Long id,
                                         @RequestParam Long userPhone,
                                         @RequestParam Integer state){
        seckillService.setState(id, userPhone, state);
        return new SucceedCommonResult<>();
    }

    @GetMapping("/acquireSeckilledByPhone")
    public CommonResult<List<Seckilled>> acquireSeckilledByPhone(@RequestParam Long userPhone){
        List<Seckilled> list = seckillService.acquireSeckilledByPhone(userPhone);
        return new SucceedCommonResult<>(list);
    }

    @GetMapping("/acquireSeckilledByPrimaryKey")
    public CommonResult<List<Seckilled>> acquireSeckilledByPrimaryKey(List<Seckilled> SeckilledList){
        List<Long> seckillListIdList = new LinkedList<>();
        SeckilledList.forEach(seckilled -> {
            // 先从缓存中拿goods信息
//            CommonResult<Goods> goodsResult = cacheService.getGoodsByKey("goods:"+seckilled.getId());
            seckillListIdList.add(seckilled.getId());
        });
        List<Seckilled> list = seckillService.acquireSeckilledByPrimaryKey(seckillListIdList,SeckilledList.get(0).getUserPhone());
        return new SucceedCommonResult<>(list);
    }
}