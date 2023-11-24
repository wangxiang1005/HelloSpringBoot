package com.paladin.jwt.controller;

import com.paladin.jwt.service.UserService;
import com.paladin.jwt.vo.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginReq req){
        return userService.login(req);
    }

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/get-product-list")
    public Object getProductList(){
        return getResult(200,"查询商品列表");
    }

    /**
     * 查询订单列表
     * @return
     */
    @GetMapping("/get-order-list")
    public Object getOrderList(){
        return getResult(200,"查询订单列表");
    }

    /**
     * 测试返回结果
     * @param code
     * @param msg
     * @return
     */
    private Object getResult(int code, String msg) {
        Map<String,Object> result = new HashMap<>();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }
}