package com.paladin.order.server.service;

import com.paladin.order.server.config.OrderConfirmRequest;

public interface OrderService {

    /**
     * 统一下单接口
     * @param orderConfirmRequest
     */
    void confirm(OrderConfirmRequest orderConfirmRequest);
}