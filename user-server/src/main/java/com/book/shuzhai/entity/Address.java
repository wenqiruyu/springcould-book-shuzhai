package com.book.shuzhai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    // 地址状态 -1表示删除 0表示默认地址 1表示使用地址
    private Integer status;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    // 最后一次更改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Address(){}

    public Address(Long id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public Address(Long id, Long userId, String consigneeName, String consigneePhone, String provinces, String detail, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.consigneeName = consigneeName;
        this.consigneePhone = consigneePhone;
        this.provinces = provinces;
        this.detail = detail;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
