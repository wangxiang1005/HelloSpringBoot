package com.paladin.skywalking.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.paladin.skywalking.order.bean.Order;
import com.paladin.skywalking.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}