package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.Order;
import com.book.shuzhai.mapper.IOrderMapper;
import com.book.shuzhai.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;

    @Override
    public int addOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderMapper.queryAllOrder();
    }

    @Override
    public List<Order> getAllOrderGroup(Long userId) {
        return orderMapper.queryAllOrderGroup(userId);
    }
}
