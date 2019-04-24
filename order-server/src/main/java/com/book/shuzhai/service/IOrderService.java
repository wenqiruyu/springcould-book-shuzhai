package com.book.shuzhai.service;

import com.book.shuzhai.entity.Order;

import java.util.List;

public interface IOrderService {

    int addOrder(Order order);

    List<Order> getAllOrder();

    List<Order> getAllOrderGroup(Long userId);
}
