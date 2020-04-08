package com.as.seckill.service.impl;

import com.as.seckill.Goods;
import com.as.seckill.dao.GoodsMapper;
import com.as.seckill.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 添加商品
     *
     * @param goods 商品
     */
    @Override
    public void add(Goods goods) {
        goodsMapper.insert(goods);
    }

    /**
     * 批量添加商品
     *
     * @param goodsList 商品列表
     */
    @Override
    public void add(List<Goods> goodsList) {
        goodsMapper.insertWithList(goodsList);
    }

    /**
     * 根据查询商品列表
     *
     * @param goodsName 商品名称
     */
    @Override
    public List<Goods> acquireByName(String goodsName) {
        return goodsMapper.selectByName(goodsName);
    }

    /**
     * 修改商品信息
     *
     * @param goods 要修改的商品信息
     */
    @Override
    public void modifyGoods(Goods goods) {
        goodsMapper.update(goods);
    }

    /**
     * 修改某个商品的库存
     *
     * @param id     商品库存
     * @param number 库存数
     */
    @Override
    public void modifyGoodsStock(Long id, Integer number) {
        goodsMapper.update(Goods.builder()
                .id(id)
                .goodsStock(number)
                .build());
    }

    /**
     * 修改某件商品的价格
     *
     * @param id         商品标识
     * @param goodsPrice 价格
     */
    @Override
    public void modifyGoodsPrice(Long id, BigDecimal goodsPrice) {
        goodsMapper.update(Goods.builder().
                id(id).
                goodsPrice(goodsPrice)
                .build());
    }

    /**
     * 删除某个商品
     *
     * @param id 商品标识
     */
    @Override
    public void deleteById(Long id) {
        goodsMapper.deleteById(id);
    }

    /**
     * 批量删除某些商品
     *
     * @param idList 批量删除的商品标识
     */
    @Override
    public void deleteByIdList(List<Long> idList) {
        goodsMapper.deleteByIdList(idList);
    }
}
