package com.paladin.order.server.config;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderConfirmRequest {

    /**
     * 支付价格
     */
    private BigDecimal totalAmount;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 支付商品的ID
     */
    private Integer productId;

    /**
     * 支付时用的优惠券ID
     */
    private Integer couponRecordId;
}