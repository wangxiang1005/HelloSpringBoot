package com.paladin.stream.util;

import com.paladin.stream.vo.Order;
import com.paladin.stream.vo.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample4 {
    public static void main(String[] args) {

        /**
         * 当处理大数据时,使用并行流可以大大提高程序的执行效率.
         * 与普通的串行流相比,使用并行流只需要在调用parallel()方法之后即可实现并行处理.
         * 需要注意的是,并行流并不适合所有场景,对于数据量较小且计算简单的情况,使用并行流反而可能导致效率下降.
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

        // 使用并行流转换订单列表为商品列表
        List<Product> products = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .parallel()
                .collect(Collectors.groupingBy(Product::getName, Collectors.summingInt(Product::getQuantity)))
                .entrySet().stream()
                .map(entry -> new Product(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        System.out.println("转换后的商品列表：");
        products.forEach(System.out::println);
    }
}