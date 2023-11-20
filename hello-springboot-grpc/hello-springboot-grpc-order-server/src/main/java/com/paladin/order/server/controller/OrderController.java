package com.paladin.order.server.controller;

import com.paladin.grpc.common.response.BaseResponse;
import com.paladin.order.server.config.OrderConfirmRequest;
import com.paladin.order.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/confirm")
    public BaseResponse confirm(@RequestBody OrderConfirmRequest orderConfirmRequest) {
        orderService.confirm(orderConfirmRequest);
        return BaseResponse.buildSuccess();
    }

    @RequestMapping("/search")
    @ResponseBody
    public String search() {
        return "======Hello======";
    }
}