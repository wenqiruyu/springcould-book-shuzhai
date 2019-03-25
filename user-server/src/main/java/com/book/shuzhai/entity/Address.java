package com.book.shuzhai.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Address {

    private Long id;
    // 所属用户id
    private Long userId;
    // 收件人名称
    private String consigneeName;
    // 收件人号码
    private String consigneePhone;
    // 地址所属省市区
    private String provinces;
    // 详细地址
    private String detail;
    // 地址状态 1表示使用 0表示删除
    private Integer status;
    // 创建时间
    private Date createTime;
    // 最后一次更改时间
    private Date updateTime;

}
