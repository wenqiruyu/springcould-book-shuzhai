package com.book.shuzhai.service;

import com.book.shuzhai.entity.SmsConfig;

import java.util.Map;

public interface ISmsService {

    // 验证码发送成功返回1 失败0 注册使用
    String sendSms(String phone, SmsConfig smsConfig, Map<String,String> map);

    SmsConfig querySmsConfig(String name);
}
