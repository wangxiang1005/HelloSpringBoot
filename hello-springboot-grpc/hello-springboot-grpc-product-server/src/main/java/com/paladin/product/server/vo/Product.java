package com.paladin.product.server.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品实体类
 */
@Data
public class Product {

    /**
     * 商品ID
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;
}