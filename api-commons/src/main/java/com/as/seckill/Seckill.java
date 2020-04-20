package com.as.seckill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seckill {
    // 秒杀标识
    private Long id;
    //商品名称
    private String goodsName;
    //商品标识
    private Long goodsId;
    // 秒杀库存
    private Integer seckillStock;
    // 秒杀开始时间
    private String seckillStartTime;
    // 秒杀结束时间
    private String seckillEndTime;
    // 秒杀创建时间
    private String seckillCreationTime;
    // 秒杀价格
    private BigDecimal seckillPrice;

    private Goods goods;
    // 版本号
    private Integer version;
}
