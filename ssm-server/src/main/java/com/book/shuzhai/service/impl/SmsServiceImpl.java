package com.book.shuzhai.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.book.shuzhai.entity.SmsConfig;
import com.book.shuzhai.mapper.SmsConfigMapper;
import com.book.shuzhai.mapper.SmsMapper;
import com.book.shuzhai.service.ISmsService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private SmsMapper smsMapper;

    @Autowired
    private SmsConfigMapper smsConfigMapper;

    @Override
    public String sendSms(String phone, SmsConfig smsConfig, Map<String,String> map) {
        try {
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient(smsConfig.getApiUrl(),smsConfig.getAppId(),smsConfig.getAppSecret());
            //向指定手机号发送短信
            String result = client.send(phone, map.get("verifyDetail"));
            JSONObject json = JSONObject.parseObject(result);
            // 短信发送失败
            if (json.getIntValue("code") != 0) {
                return "短信发送失败";
            }
            // 短信发送成功
            return map.get("verifyCode");
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    @Override
    public SmsConfig querySmsConfig(String name) {
        return smsConfigMapper.querySmsConfig(name);
    }
}
