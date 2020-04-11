package com.as.seckill.controller;

import cn.hutool.core.util.IdUtil;
import com.as.seckill.CommonResult;
import com.as.seckill.Goods;
import com.as.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/goodsService")
public class GoodsController {
    private static final String FILEPATH = "C:\\Users\\work\\Desktop\\seckill\\upload\\";

    @Resource
    private GoodsService goodsService;

    @PostMapping("/add")
    public CommonResult<Object> add(@RequestBody Map<String ,Goods> map) {
        Goods goods = map.get("goods");
        goodsService.add(goods);
        return new CommonResult<>(200, "增加成功(*￣︶￣)", goods.getId());
    }

    @GetMapping("/acquire")
    public CommonResult<List<Goods>> acquireByName(@RequestParam String goodsName) {
        List<Goods> goodsList = goodsService.acquireByName(goodsName);
        return new CommonResult<>(200, "查询到了" + goodsList.size() + "条数据", goodsList);
    }

    @GetMapping("/acquireById")
    public CommonResult<Goods> acquireById(@RequestParam Long id) {
        Goods goods= goodsService.acquireById(id);
        return new CommonResult<>(200, "查询到了 1 条数据", goods);
    }

    @PostMapping("/modifyGoods")
    public CommonResult<Object> modifyGoods(@RequestBody Map<String, Goods> map) {
        Goods goods = map.get("goods");
        Integer semaphore = goodsService.modifyGoods(goods);
        if(semaphore != 1){
            return new CommonResult<>(100, "要修改的数据不存在");
        }
        return new CommonResult<>(200, "修改成功(*￣︶￣)");
    }

    @PostMapping("/modifyGoodsStock/{mode}")
    public CommonResult<Object> modifyGoodsStock(@RequestParam Long id,
                                                 @RequestParam Integer number,
                                                 @PathVariable String mode) {
        if("increase".equals(mode)){
            goodsService.increaseGoodsStock(id, number);
        }else {
            goodsService.decreaseGoodsStock(id, number);
        }
        return new CommonResult<>(200, "修改成功");
    }



    @PostMapping("/modifyGoodsPrice")
    public CommonResult<Object> modifyGoodsPrice(@RequestParam Long id,
                                                 @RequestParam BigDecimal goodsPrice){
        Integer semaphore = goodsService.modifyGoodsPrice(id, goodsPrice);
        if(semaphore != 1){
            return new CommonResult<>(100, "要修改的数据不存在");
        }
        return new CommonResult<>(200, "修改成功");
    }

    @PostMapping("/deleteById")
    public CommonResult<Object> deleteById(@RequestBody Map<String, Long> map){
        goodsService.deleteById(map.get("id"));
        return new CommonResult<>(200, "删除成功！");
    }

    @PostMapping("/deleteByIdList")
    public CommonResult<Object> deleteByIdList(@RequestBody Map<String, List<Long>> map){
        List<Long> idList = map.get("idList");
        goodsService.deleteByIdList(idList);
        return new CommonResult<>(200, "删除成功！");
    }

    @PostMapping("/deleteByIdArray")
    public CommonResult<Object> deleteByIdArray(@RequestParam Long[] idArray){
        goodsService.deleteByIdArray(idArray);
        return new CommonResult<>(200, "删除成功！");
    }

    @PostMapping("/upload")
    public CommonResult<Object> upload(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()) {
            return new CommonResult<>(444, "上传失败！");
        }
        String id = IdUtil.randomUUID();
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.indexOf("."));
        String reName = id + extension;
        File dest = new File(FILEPATH + reName);
        try {
            file.transferTo(dest);
            return new CommonResult<>(200, "上传成功", reName);
        } catch (IOException e) {
            return new CommonResult<>(444, "上传失败！");
        }
    }
}
