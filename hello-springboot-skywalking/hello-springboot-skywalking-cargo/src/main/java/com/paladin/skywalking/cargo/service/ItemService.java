package com.paladin.skywalking.cargo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.paladin.skywalking.cargo.bean.Item;

public interface ItemService extends IService<Item> {
    Integer incr(Integer id, Integer count);
}