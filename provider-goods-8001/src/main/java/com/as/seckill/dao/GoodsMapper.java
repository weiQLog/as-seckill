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
     * 根据名称查询商品列表
     * @param goodsName 商品名称
     * @return 商品列表
     */
    List<Goods> selectByName(@Param("goodsName") String goodsName);

    /**
     * 根据id查询商品信息
     * @param id 商品标识
     * @return 返回一个商品信息
     */
    Goods selectById(Long id);

    /**
     * 修改商品信息
     * @param goods 商品
     * @return id
     */
    Integer update(Goods goods);

    /**
     * 增加库存
     * @param id 商品id
     * @param number 增量
     */
    void increaseStock(@Param("id") Long id, @Param("number") Integer number);

    /**
     * 减少库存
     * @param id 减量
     * @param number 减量
     * @return
     */
    void decreaseStock(@Param("id") Long id, @Param("number") Integer number);

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