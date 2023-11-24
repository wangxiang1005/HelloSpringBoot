package com.paladin.redis;

import com.paladin.redis.service.ShopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class RedisApplication {
    @Resource
    private ShopService shopService;

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @PostConstruct
    public void syncShopLocationToRedis(){
        shopService.syncShopLocationData();
    }
}