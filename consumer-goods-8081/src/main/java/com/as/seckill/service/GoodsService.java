package com.as.seckill.service;

import com.as.seckill.Goods;
import com.as.seckill.result.CommonResult;
import com.as.seckill.service.fallback.GoodsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Component
@FeignClient(value = "provider-goods", fallback = GoodsServiceFallback.class)
public interface GoodsService {

    /**
     * 增加商品
     * @param goods 商品
     * @return CommonResult
     */
    @PostMapping("/goodsService/add")
    public CommonResult<Object> add(@SpringQueryMap Goods goods);

    /**
     * 根据商品名称查询商品
     * @param goodsName 商品名称
     * @return CommonResult
     */
    @GetMapping("/goodsService/acquireByName")
    public CommonResult<List<Goods>> acquireByName(@RequestParam("goodsName") String goodsName);

    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return CommonResult
     */
    @GetMapping("/goodsService/acquireById")
    public CommonResult<Goods> acquireById(@RequestParam("id") Long id) ;

    /**
     * 修改商品信息
     * @param goods 要修改的信息
     * @return CommonResult
     */
    @PostMapping("/goodsService/modifyGoods")
    public CommonResult<Object> modifyGoods(@SpringQueryMap Goods goods);

    /**
     * 增加库存
     * @param id 商品id
     * @param number 增加/的库存量
     * @return CommonResult
     */
    @PostMapping("/goodsService/modifyGoodsStock/increase")
    public CommonResult<Object> increaseGoodsStock(@RequestParam("id") Long id,
                                                   @RequestParam("number") Integer number);

    /**
     * 减少 库存
     * @param id 商品id
     * @param number 减少的库存量
     * @return CommonResult
     */
    @PostMapping("/goodsService/modifyGoodsStock/decrease")
    public CommonResult<Object> decreaseGoodsStock(@RequestParam("id") Long id,
                                                   @RequestParam("number") Integer number);

    @PostMapping("/goodsService/modifyGoodsPrice")
    public CommonResult<Object> modifyGoodsPrice(@RequestParam("id") Long id,
                                                  @RequestParam("goodsPrice") BigDecimal goodsPrice);
    /**
     * 根据商品id删除商品
     * @param id 商品id
     * @return CommonResult
     */
    @PostMapping("/goodsService/deleteById")
    public CommonResult<Object> deleteById(@RequestParam("id") Long id);

    /**
     * 根据id列表，批量删除商品信息
     * @param idList 商品id列表
     * @return CommonResult
     */
    @PostMapping("/goodsService/deleteByIdList")
    public CommonResult<Object> deleteByIdList(List<Long> idList);

    /**
     * 上传图片
     * @param file 图片文件
     * @return CommonResult 成功返回文件名
     */
    @PostMapping(value = "/goodsService/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Object> upload(@RequestPart("file") MultipartFile file);


    /**
     * 获取版本
     * @param id 商品id
     * @return 商品版本
     */
    @GetMapping("/goodsService/version")
    public CommonResult<Object> getVersion(@RequestParam("id") Long id);

}
