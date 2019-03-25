package com.book.shuzhai.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sms {

    private Long id;

    private String phone;
    // 验证码
    private String verifyCode;
    // 短信类型 0为注册验证短信 1为注册提示 2为登录验证短信 3为广告短
    private Integer type;
    // 短信状态
    private Integer status;

    private String detail;

    private Date createTime;

    public Sms() { }

    public Sms(String verifyCode, Integer status, String detail, Date createTime) {
        this.verifyCode = verifyCode;
        this.status = status;
        this.detail = detail;
        this.createTime = createTime;
    }

    public Sms(String phone, String verifyCode, Integer type, Integer status, String detail, Date createTime) {
        this.phone = phone;
        this.verifyCode = verifyCode;
        this.type = type;
        this.status = status;
        this.detail = detail;
        this.createTime = createTime;
    }
}
