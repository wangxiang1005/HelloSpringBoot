package com.paladin.order.server.config;

import com.paladin.grpc.server.CouponServiceGrpc;
import com.paladin.grpc.server.ProductServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description Grpc Client 配置类
 */
@Configuration
public class GrpcClientConfig {

    /**
     * 商品服务地址
     */
    @Value("${product.server.address}")
    private String productServerAddress;

    /**
     * 优惠券服务地址
     */
    @Value("${coupon.server.address}")
    private String couponServerAddress;

    /**
     * 商品服务grpc-client
     * @return
     */
    @Bean
    public ProductServiceGrpc.ProductServiceBlockingStub getProductServerClient() {
        return ProductServiceGrpc.newBlockingStub(ManagedChannelBuilder.forTarget(productServerAddress).usePlaintext().build());
    }

    /**
     * 优惠卷服务grpc-client
     * @return
     */
    @Bean
    public CouponServiceGrpc.CouponServiceBlockingStub getCouponServerClient() {
        return CouponServiceGrpc.newBlockingStub(ManagedChannelBuilder.forTarget(couponServerAddress).usePlaintext().build());
    }
}