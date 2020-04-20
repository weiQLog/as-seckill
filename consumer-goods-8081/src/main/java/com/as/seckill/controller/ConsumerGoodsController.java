package com.as.seckill.controller;

import com.as.seckill.result.CommonResult;
import com.as.seckill.Goods;
import com.as.seckill.result.NoContentCommonResult;
import com.as.seckill.result.VersionWrongCommonResult;
import com.as.seckill.service.GoodsService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@Slf4j
@RequestMapping("/goods")
public class ConsumerGoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("/add")
    public CommonResult<Object> add(@RequestBody Map<String, Goods> map) {
        Goods goods = map.get("goods");
        return goodsService.add(goods);
    }

    @GetMapping("/acquireByName")
    public CommonResult<List<Goods>> acquireByName(@RequestParam String goodsName) {
        return goodsService.acquireByName(goodsName);
    }

    @GetMapping("/acquireById")
    public CommonResult<Goods> acquireById(@RequestParam Long id) {
        return goodsService.acquireById(id);
    }


    @GlobalTransactional(name = "modify-goods", rollbackFor = Exception.class)
    @PostMapping("/modifyGoods")
    public CommonResult<Object> modifyGoods(@RequestBody Map<String, Goods> map) {
        Goods goods = map.get("goods");
        return compareVersion(goods.getId(), goods.getVersion(), () -> {
            return goodsService.modifyGoods(goods);
        });
    }

    @GlobalTransactional(name = "modify-goods", rollbackFor = Exception.class)
    @PostMapping("/modifyGoodsStock/increase")
    public CommonResult<Object> increaseGoodsStock(@RequestBody Map<String, Object> map) {
        Long id = (Long) map.get("id");
        Integer version = (Integer) map.get("version");
        Integer number = (Integer) map.get("number");
        return compareVersion(id, version, () -> {
            return goodsService.increaseGoodsStock(id, number);
        });
    }

    @GlobalTransactional(name = "modify-goods", rollbackFor = Exception.class)
    @PostMapping("/modifyGoodsStock/decrease")
    public CommonResult<Object> decreaseGoodsStock(@RequestBody Map<String, Object> map) {
        Long id = (Long) map.get("id");
        Integer version = (Integer) map.get("version");
        Integer number = (Integer) map.get("number");
        return compareVersion(id, version, () -> {
            return goodsService.decreaseGoodsStock(id, number);
        });
    }

    @PostMapping("/deleteById")
    public CommonResult<Object> deleteById(@RequestBody Map<String, Long> map) {
        Long id = map.get("id");
        return goodsService.deleteById(id);
    }

    @PostMapping("/deleteByIdList")
    public CommonResult<Object> deleteByIdList(@RequestBody Map<String, List<Long>> map) {
        List<Long> idList = map.get("idList");
        return goodsService.deleteByIdList(idList);
    }

    @PostMapping("/upload")
    public CommonResult<Object> upload(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new CommonResult<>(444, "上传失败！");
        }
        return goodsService.upload(file);
    }

    /**
     * 比较版本
     */
    public CommonResult<Object> compareVersion(Long id, Integer oldVersion, Supplier<CommonResult<Object>> supplier) {
        CommonResult<Object> result = goodsService.getVersion(id);
        if (!result.getCode().equals(200)) { // 如果获取版本时出错， 则返回错误信息
            return result;
        }
        Integer version = (Integer) result.getData();
        if (version == null) { // 版本为空，数据不存在？ 返回NoContentCommonResult
            return new NoContentCommonResult<>();
        } else if (!version.equals(oldVersion)) { // 版本不一致 返回VersionWrongCommonResult
            return new VersionWrongCommonResult<>();
        } else {
            return supplier.get();
        }
    }

}
