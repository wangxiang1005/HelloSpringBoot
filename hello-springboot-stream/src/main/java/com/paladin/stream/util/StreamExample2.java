package com.paladin.stream.util;

import com.paladin.stream.vo.Order;
import com.paladin.stream.vo.Product;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample2 {
    public static void main(String[] args) {

        /**
         * 使用串行流
         * 在不同的数据类型之间进行转换时,可以使用Java 8中提供的map()方法来实现.
         *
         */

        // 准备订单数据
        List<Order> orders = Arrays.asList(
                new Order("O001", Arrays.asList(
                        new Product("P001", 2),
                        new Product("P002", 3)
                )),
                new Order("O002", Arrays.asList(
                        new Product("P003", 4),
                        new Product("P004", 1),
                        new Product("P005", 2)
                ))
        );

        // 转换订单列表为商品列表
        List<Product> products = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(Product::getName, Collectors.summingInt(Product::getQuantity)))
                .entrySet().stream()
                .map(entry -> new Product(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        System.out.println("转换后的商品列表：");
        products.forEach(System.out::println);
    }
}