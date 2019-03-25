package com.book.shuzhai.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SmsConfig {

    private Long id;

    private String name;

    private String apiUrl;

    private String appId;

    private String appSecret;

    private Date createTime;

    private Date updateTime;

    public SmsConfig(){}

    public SmsConfig(String apiUrl, String appId, String appSecret) {
        this.apiUrl = apiUrl;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public SmsConfig(String name, String apiUrl, String appId, String appSecret, Date createTime, Date updateTime) {
        this.name = name;
        this.apiUrl = apiUrl;
        this.appId = appId;
        this.appSecret = appSecret;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
