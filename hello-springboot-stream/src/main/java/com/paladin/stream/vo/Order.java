package com.paladin.stream.vo;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private List<Product> products;

    public Order(String orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
    }
}