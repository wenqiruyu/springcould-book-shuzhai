package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Order;

import java.util.List;

public interface IOrderMapper {

    int insertOrder(Order order);

    List<Order> queryAllOrder();

//    按照日期分组查询
    List<Order> queryAllOrderGroup(Long userId);
}
