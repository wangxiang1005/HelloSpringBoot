package com.paladin.skywalking.cargo.controller;

import com.paladin.skywalking.cargo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cargo")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 库存递减
     */
    @GetMapping(value = "/{id}/{count}")
    public Integer incrCount(@PathVariable(value = "id")Integer id,
                             @PathVariable(value = "count")Integer count){
        return itemService.incr(id,count);
    }
}