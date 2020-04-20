package com.as.seckill.controller.limit;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.as.seckill.Seckilled;
import com.as.seckill.result.CommonResult;

import java.util.Map;

public class BlockHandler {
    public static CommonResult<Object> createSeckilled(Map<String, Seckilled> map, BlockException ex){
        return new CommonResult<>(444, "没有抢到哦！");

    }

}
