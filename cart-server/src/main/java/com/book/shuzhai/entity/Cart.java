package com.book.shuzhai.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {

    private Long id;
    // 所属用户id
    private Long userId;
    // 商品id
    private Long productId;
    // 商品数量
    private Long quantity;
    // 是否进行勾选 1表示勾选 0表示未勾选
    private Integer checked;
    // 购物车状态0表示删除1表示使用
    private Integer status;

    public Date createTime;

    public Date updateTime;

    public Product product;

    public Cart(){}

    public Cart(Long userId, Long productId, Long quantity, Integer checked, Integer status) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
        this.status = status;
    }

    public Cart(Long userId, Long productId, Long quantity, Integer checked, Integer status, Date createTime, Date updateTime) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
