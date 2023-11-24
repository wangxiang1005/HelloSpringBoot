package com.paladin.redis.config;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ShopVO {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 精度
     */
    private BigDecimal accuracy;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 店铺星级
     */
    private String star;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 距离
     */
    private double distance;
}