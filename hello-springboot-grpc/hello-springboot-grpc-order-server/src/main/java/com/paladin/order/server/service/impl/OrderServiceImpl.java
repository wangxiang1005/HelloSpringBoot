package com.paladin.order.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.paladin.grpc.server.*;
import com.paladin.order.server.config.OrderConfirmRequest;
import com.paladin.order.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductServiceGrpc.ProductServiceBlockingStub productService;

    @Autowired
    private CouponServiceGrpc.CouponServiceBlockingStub couponService;

    @Override
    public void confirm(OrderConfirmRequest orderConfirmRequest) {
        //1.调用优惠券服务获取优惠券详情
        DeductCouponRequest deductCouponRequest = DeductCouponRequest.newBuilder()
                .setCouponId(orderConfirmRequest.getCouponRecordId())
                .build();
        CouponServerResponse couponServerResponse = couponService.deductProductInventory(deductCouponRequest);
        String couponDataStr = couponServerResponse.getData();
        JSONObject couponData = JSON.parseObject(couponDataStr);
        log.info("调用优惠卷服务获取的优惠券信息：{}",couponData);

        //2.调用商品服务获取商品详细信息
        DeductInventoryRequest deductInventoryRequest = DeductInventoryRequest.newBuilder()
                .setProductId(orderConfirmRequest.getProductId())
                .build();
        ProductServerResponse productServerResponse = productService.deductProductInventory(deductInventoryRequest);
        String productDataStr = productServerResponse.getData();
        JSON.parseObject(productDataStr);
        JSONObject productData = JSON.parseObject(productDataStr);
        log.info("调用商品服务获取的商品信息：{}",productData);

        //3.判断优惠券是否在使用时间范围内
        long today = new Date().getTime();
        long startTime = couponData.getDate("startTime").getTime();
        long endTime = couponData.getDate("endTime").getTime();

        System.out.println("today===>>>>"+today);
        System.out.println("startTime===>>>>"+startTime);
        System.out.println("endTime===>>>>"+endTime);

        if(startTime>today || endTime<today){
            throw new RuntimeException("当前优惠券不在可用范围内");
        }

        //4.验证价格
        BigDecimal amount = couponData.getBigDecimal("amount");
        BigDecimal price = productData.getBigDecimal("price");

        if(!price.subtract(amount).equals(orderConfirmRequest.getTotalAmount())){
            throw new RuntimeException("订单验价失败");
        }

        //5.生成订单
        log.info("当前订单购买的商品为:{},原价为:{},本次消耗优惠券:{},实际支付金额:{}",
                productData.getString("name"),productData.getBigDecimal("price"),
                couponData.getString("name"),orderConfirmRequest.getTotalAmount());
    }
}