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

    public Date createTime;

    public Date updateTime;

    public Product product;

    public Cart(){}

    public Cart(Long userId, Long productId, Long quantity, Integer checked) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
    }

    public Cart(Long userId, Long productId, Long quantity, Integer checked, Date createTime, Date updateTime) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.checked = checked;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
