package com.book.shuzhai.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private String rankTime;

    private Long id;
    // 订单编号，唯一，使用UUID
    private String orderNumber;
    private Long userId;
    // 购物车id集，使用逗号分割
    private String cartId;
    private Long productId;
    private Long addressId;
    private Long quantity;
    // 订单金额
    private BigDecimal orderMoney;
    // 订单状态 0表示用户删除订单 1表示未支付 2表示支付成功未发货 3表示运输中 4表示用户未确定收货 5表示用户收货成功
    private Integer status;
    // 付款时间
    private Date payTime;
    private Date createTime;
    private List<Product> product;

    public Order(){}

    public Order(String orderNumber, Long userId, String cartId, Long productId, Long addressId, Long quantity, BigDecimal orderMoney, Integer status, Date payTime, Date createTime) {
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.cartId = cartId;
        this.productId = productId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.orderMoney = orderMoney;
        this.status = status;
        this.payTime = payTime;
        this.createTime = createTime;
    }

    public Order(Long id, String orderNumber, Long userId, String cartId, Long productId, Long addressId, Long quantity, BigDecimal orderMoney, Integer status, Date payTime, Date createTime) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.cartId = cartId;
        this.productId = productId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.orderMoney = orderMoney;
        this.status = status;
        this.payTime = payTime;
        this.createTime = createTime;
    }

    public Order(String rankTime, Long id, String orderNumber, Long userId, String cartId, Long productId, Long addressId, Long quantity, BigDecimal orderMoney, Integer status, Date payTime, Date createTime) {
        this.rankTime = rankTime;
        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.cartId = cartId;
        this.productId = productId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.orderMoney = orderMoney;
        this.status = status;
        this.payTime = payTime;
        this.createTime = createTime;
    }

    public Order(String rankTime, Long id, String orderNumber, Long userId, String cartId, Long productId, Long addressId, Long quantity, BigDecimal orderMoney, Integer status, Date payTime, Date createTime, List<Product> product) {
        this.rankTime = rankTime;
        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.cartId = cartId;
        this.productId = productId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.orderMoney = orderMoney;
        this.status = status;
        this.payTime = payTime;
        this.createTime = createTime;
        this.product = product;
    }
}
