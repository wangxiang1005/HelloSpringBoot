package com.paladin.aop.controller;

import com.paladin.aop.aspect.UserAccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/first")
    public Object first() {
        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

	@RequestMapping("/second")
    @UserAccess(desc = "second")
    public Object second() {
        return "second controller";
    }
}