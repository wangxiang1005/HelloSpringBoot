package com.paladin.skywalking.cargo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paladin.skywalking.cargo.bean.Item;
import com.paladin.skywalking.cargo.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    /**
     * 库存递减
     */
    @Override
    public Integer incr(Integer id, Integer count) {
        return itemMapper.updateItem(id,count);
    }
}