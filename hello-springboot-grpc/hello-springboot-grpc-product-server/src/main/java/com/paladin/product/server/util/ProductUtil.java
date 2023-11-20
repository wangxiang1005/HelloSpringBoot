package com.paladin.product.server.util;

import com.paladin.product.server.vo.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductUtil {

    private final static List<Product> productList = new ArrayList<>();

    static {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("互联网JAVA架构师养成零基础到精通");
        product1.setPrice(new BigDecimal(12999));

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Python+大数据零基础到精通");
        product2.setPrice(new BigDecimal(15999));

        Product product3 = new Product();
        product3.setId(3);
        product3.setName("5G云计算运维架构零基础到精通");
        product3.setPrice(new BigDecimal(10999));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
    }

    /**
     * 根据商品ID获取商品详情
     * @param productId
     * @return
     */
    public static Product getProductById(Integer productId){
        return productList.stream().filter(obj-> Objects.equals(obj.getId(), productId)).collect(Collectors.toList()).get(0);
    }
}