package com.paladin.skywalking.order.controller;

import com.paladin.skywalking.order.bean.Order;
import com.paladin.skywalking.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     */
    @GetMapping(value = "/{id}/{count}")
    public String add(@PathVariable(value = "id")Integer id, @PathVariable(value = "count")Integer count){
        System.out.println("=====id====="+id);
        System.out.println("=====count====="+count);
        //1)递减库存
        Integer hCount = restTemplate.getForObject("http://localhost:18082/cargo/1/1", Integer.class);
        System.out.println("=====hCount====="+hCount);
        //2)下单
        if(hCount>0){
            Order order = new Order();
            order.setCount(count);
            order.setItemId(id);
            orderService.save(order);
        }
        return "SUCCESS";
    }
}