package com.as.seckill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods implements Serializable {
    // 商品ID
    private Long id;
    // 商品名称
    private String goodsName;
    // 商品标题
    private String goodsTitle;
    // 商品图片
    private String goodsImg;
    // 商品描述
    private String goodsDetail;
    // 商品单价
    private BigDecimal goodsPrice;
    // 商品库存
    private Integer goodsStock;
    //版本号
    private Integer version;

}
