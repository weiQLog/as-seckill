package com.as.seckill.service.fallback;

import com.as.seckill.result.CommonResult;
import com.as.seckill.Goods;
import com.as.seckill.result.ServerErrorCommonResult;
import com.as.seckill.service.GoodsService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * GoodsService的降级处理
 */
@Component
public class GoodsServiceFallback implements GoodsService {
    /**
     * 增加商品
     *
     * @param goods 商品
     * @return CommonResult
     */
    @Override
    public CommonResult<Object> add(Goods goods) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 根据商品名称查询商品
     *
     * @param goodsName 商品名称
     * @return CommonResult
     */
    @Override
    public CommonResult<List<Goods>> acquireByName(String goodsName) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 根据商品id查询商品
     *
     * @param id 商品id
     * @return CommonResult
     */
    @Override
    public CommonResult<Goods> acquireById(Long id) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 修改商品信息
     *
     * @param goods 要修改的信息
     * @return CommonResult
     */
    @Override
    public CommonResult<Object> modifyGoods(Goods goods) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 增加库存
     *
     * @param id     商品id
     * @param number 增加/的库存量
     * @return CommonResult
     */
    @Override
    public CommonResult<Object> increaseGoodsStock(Long id, Integer number) {
        return new ServerErrorCommonResult<>();
    }
    /**
     * 减少 库存
     *
     * @param id     商品id
     * @param number 减少的库存量
     * @return CommonResult
     */
    @Override
    public CommonResult<Object> decreaseGoodsStock(Long id, Integer number) {
        return new ServerErrorCommonResult<>();
    }

    @Override
    public CommonResult<Object> modifyGoodsPrice(Long id, BigDecimal goodsPrice) {
        return new ServerErrorCommonResult<>();
    }


    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return CommonResult
     */
    @Override
    public CommonResult<Object> deleteById(Long id) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 根据id列表，批量删除商品信息
     *
     * @param idList 商品id列表
     * @return CommonResult
     */
    @Override
    public CommonResult<Object> deleteByIdList(List<Long> idList) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return CommonResult 成功返回文件名
     */
    @Override
    public CommonResult<Object> upload(MultipartFile file) {
        return new ServerErrorCommonResult<>();
    }

    /**
     * 获取版本
     *
     * @param id 商品id
     * @return 商品版本
     */
    @Override
    public CommonResult<Object> getVersion(Long id) {
        return new ServerErrorCommonResult<>();
    }
}
