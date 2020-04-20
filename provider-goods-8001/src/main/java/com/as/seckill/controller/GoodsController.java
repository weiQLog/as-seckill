package com.as.seckill.controller;

import cn.hutool.core.util.IdUtil;
import com.as.seckill.result.CommonResult;
import com.as.seckill.Goods;
import com.as.seckill.result.NoContentCommonResult;
import com.as.seckill.result.SucceedCommonResult;
import com.as.seckill.result.VersionWrongCommonResult;
import com.as.seckill.service.GoodsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

@RestController
@Slf4j
@RequestMapping("/goodsService")
public class GoodsController {
    private static final String FILEPATH = "C:\\Users\\work\\Pictures\\Saved Pictures\\background\\";

    @Resource
    private GoodsService goodsService;

    @PostMapping("/add")
    public CommonResult<Object> add(Goods goods) {
        goodsService.add(goods);
        return new SucceedCommonResult<>();
    }

    @GetMapping("/acquireByName")
    public CommonResult<List<Goods>> acquireByName(@RequestParam String goodsName) {
        List<Goods> list = goodsService.acquireByName(goodsName);
        return new SucceedCommonResult<>(list);
    }

    @GetMapping("/acquireById")
    public CommonResult<Goods> acquireById(@RequestParam Long id) {
        Goods goods = goodsService.acquireById(id);
        return new SucceedCommonResult<>(goods);
    }

    @PostMapping("/modifyGoods")
    public CommonResult<Integer> modifyGoods(Goods goods) {
        Integer semaphore = goodsService.modifyGoods(goods);
        return new SucceedCommonResult<>(semaphore);
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
        return new SucceedCommonResult<>();
    }


    @PostMapping("/modifyGoodsPrice")
    public CommonResult<Integer> modifyGoodsPrice(@RequestParam Long id,
                                                 @RequestParam BigDecimal goodsPrice){
        Integer semaphore = goodsService.modifyGoodsPrice(id, goodsPrice);
        return new SucceedCommonResult<>(semaphore);
    }

    @PostMapping("/deleteById")
    public CommonResult<Object> deleteById(@RequestParam Long id){
        goodsService.deleteById(id);
        return new SucceedCommonResult<>();
    }

    @PostMapping("/deleteByIdList")
    public CommonResult<Object> deleteByIdList(List<Long> idList){
        goodsService.deleteByIdList(idList);
        return new SucceedCommonResult<>();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SneakyThrows
    public CommonResult<Object> upload(@RequestPart("file") MultipartFile file){
        String id = IdUtil.randomUUID();
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.indexOf("."));
        String reName = id + extension;
        File dest = new File(FILEPATH + reName);
        file.transferTo(dest);
        return new SucceedCommonResult<>();
    }

    @GetMapping("/version")
    public CommonResult<Integer> getVersion(@RequestParam Long id){
        Integer version = goodsService.getVersion(id);
        return new SucceedCommonResult<>(version);
    }
}
