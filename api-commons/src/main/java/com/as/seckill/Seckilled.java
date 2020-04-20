package com.as.seckill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 秒杀成功信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seckilled {
    private Long id;
    private Long userPhone;
    private Long goodsId;
    private Integer seckilledState;
    private String seckilledCreationTime;
    private BigDecimal seckilledPrice;
    private Integer seckilledNumber;
    private Seckill seckill;
    private Goods goods;

}
