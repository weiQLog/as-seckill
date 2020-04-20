package com.as.seckill.service.impl;

import com.as.seckill.Goods;
import com.as.seckill.dao.GoodsMapper;
import com.as.seckill.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
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
        log.info("[{}]", goods.toString());
        goodsMapper.insert(goods);
    }

    /**
     * 根据查询商品列表
     *
     * @param goodsName 商品名称
     */
    @Override
    public List<Goods> acquireByName(String goodsName) {
        return goodsMapper.selectByName("%" + goodsName + "%");
    }

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品标识
     * @return 返回商品信息
     */
    @Override
    public Goods acquireById(Long id) {
        return goodsMapper.selectById(id);
    }

    /**
     * 修改商品信息
     *
     * @param goods 要修改的商品信息
     */
    @Override
    public Integer modifyGoods(Goods goods) {
        return goodsMapper.update(goods);
    }

    /**
     * 增加商品的库存
     * @param id     商品库存
     * @param number 库存数
     */
    @Override
    public void increaseGoodsStock(Long id, Integer number) {
        goodsMapper.increaseStock(id, number);
    }

    /**
     * 减少库存
     * @param id 商品标识
     */
    @Override
    public void decreaseGoodsStock(Long id, Integer number) {
        goodsMapper.decreaseStock(id, number);
    }

    /**
     * 修改某件商品的价格
     *
     * @param id         商品标识
     * @param goodsPrice 价格
     */
    @Override
    public Integer modifyGoodsPrice(Long id, BigDecimal goodsPrice) {
        return goodsMapper.update(Goods.builder().
                id(id).
                goodsPrice(goodsPrice).build());
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

    /**
     * 批量删除商品
     *
     * @param idList 商品标识
     */
    @Override
    public void deleteByIdArray(Long[] idList) {
        goodsMapper.deleteByIdList(Arrays.asList(idList));
    }

    /**
     * 获取版本
     * @param id 商品id
     * @return 版本号
     */
    @Override
    public Integer getVersion(Long id) {
        return goodsMapper.getVersion(id);
    }
}
