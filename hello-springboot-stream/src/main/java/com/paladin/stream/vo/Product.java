package com.paladin.stream.vo;

import lombok.Data;

@Data
public class Product {
    private String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}