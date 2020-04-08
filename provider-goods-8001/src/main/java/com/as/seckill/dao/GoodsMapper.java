package com.as.seckill.dao;

import com.as.seckill.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    /**
     * 增加新的商品
     * @param goods 商品
     */
    void insert(Goods goods);

    /**
     * 批量增加新的商品
     * @param list 商品列表
     */
    void insertWithList(@Param("goodsList") List<Goods> list);

    /**
     * 根据名称查询商品列表
     * @param goodsName 商品名称
     * @return 商品列表
     */
    List<Goods> selectByName(@Param("goodsName") String goodsName);

    /**
     * 修改商品信息
     * @param goods 商品
     */
    void update(Goods goods);

    /**
     * 根据商品id删除商品
     * @param id 商品标识
     */
    void deleteById(@Param("id") Long id);

    /**
     * 批量删除商品信息
     * @param idList id列表
     */
    void deleteByIdList(@Param("idList") List<Long> idList);
}