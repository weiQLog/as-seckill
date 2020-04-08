package com.as.seckill.service;

import com.as.seckill.Goods;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService {
    /**
     * 添加商品
     * @param goods 商品
     */
    void add(Goods goods);

    /**
     * 批量添加商品
     * @param goodsList 商品列表
     */
    void add(List<Goods> goodsList);

    /**
     * 根据查询商品列表
     * @param goodsName 商品名称
     */
    List<Goods> acquireByName(String goodsName);

    /**
     * 修改商品信息
     * @param goods 要修改的商品信息
     */
    void modifyGoods(Goods goods);

    /**
     * 修改某个商品的库存
     * @param id 商品库存
     * @param number 库存数
     */
    void modifyGoodsStock(Long id, Integer number);

    /**
     * 修改某件商品的价格
     * @param id 商品标识
     * @param goodsPrice 价格
     */
    void modifyGoodsPrice(Long id, BigDecimal goodsPrice);

    /**
     * 删除某个商品
     * @param id 商品标识
     */
    void deleteById(Long id);

    /**
     * 批量删除某些商品
     * @param idList 批量删除的商品标识
     */
    void deleteByIdList(List<Long> idList);
}
