package com.paladin.redis.controller;

import com.paladin.redis.config.ShopVO;
import com.paladin.redis.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/hello")
    public String hello(){
        return "SUCCESS";
    }

    @GetMapping("/syncShopLocationToRedis")
    public void syncShopLocationToRedis(){
        shopService.syncShopLocationData();
    }

    @GetMapping("/getShopListByLocation")
    public List<ShopVO> getShopListByLocation(){
        //模拟用户信息
        Map<String,Object> user = new HashMap<>();
        user.put("accuracy",70.361239);
        user.put("latitude",67.115126);
        user.put("name","李祥");
        user.put("id", UUID.randomUUID().toString().replace("-",""));
        List<ShopVO> shopVO = shopService.getShopListByLocation(user);
        return shopVO;
    }
}