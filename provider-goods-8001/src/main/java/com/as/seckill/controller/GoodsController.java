package com.as.seckill.controller;

import com.as.seckill.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;
}
