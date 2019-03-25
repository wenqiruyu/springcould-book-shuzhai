package com.book.shuzhai.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.book.shuzhai.entity.Sms;
import com.book.shuzhai.entity.SmsConfig;
import com.book.shuzhai.mapper.SmsConfigMapper;
import com.book.shuzhai.mapper.SmsMapper;
import com.book.shuzhai.service.ISmsService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private SmsMapper smsMapper;

    @Autowired
    private SmsConfigMapper smsConfigMapper;

    @Override
    public int sendSms(String phone, SmsConfig smsConfig, Map<String,String> map) {
        int flag = 1;
        JSONObject json = null;
        try {
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient(smsConfig.getApiUrl(),smsConfig.getAppId(),smsConfig.getAppSecret());
            //向指定手机号发送短信
            String result = client.send(phone, map.get("verifyDetail"));
            json = JSONObject.parseObject(result);
            Sms sms = smsMapper.querySms(phone, 0);
            Sms newSms = new Sms();
            // 短信发送失败
            if (json.getIntValue("code") != 0) {
                System.out.println(json.getIntValue("code"));
                flag = 0;
            }
            // 将短信信息存储进数据库  需先查询同手机号，类型的手机短信是否存在  0注册
            if(sms == null){
                // 该部分需要进行验证码的缓存存储
                // 将短信信息存储进数据库
                newSms.setPhone(phone);
                newSms.setVerifyCode(map.get("verifyCode"));
                newSms.setType(0);
                newSms.setStatus(flag);
                newSms.setDetail(map.get("verifyDetail"));
                newSms.setCreateTime(new Date());
                smsMapper.insertSms(newSms);
            }else{
                // 数据库中短信数据是失败的
                if(sms.getStatus() == 0){
                    newSms.setVerifyCode(map.get("verifyCode"));
                    newSms.setDetail(map.get("verifyDetail"));
                    newSms.setStatus(flag);
                    newSms.setCreateTime(new Date());
                    smsMapper.updateSms(sms.getId(),newSms);
                }
            }
            // 短信发送成功
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public SmsConfig querySmsConfig(String name) {
        return smsConfigMapper.querySmsConfig(name);
    }

    @Override
    public int addSms(Sms sms) {
        return smsMapper.insertSms(sms);
    }

    @Override
    public Sms getSms(String phone, Integer type) {
        // 获取短信验证码应该是获取缓存中的验证码，以实现时效问题
        return smsMapper.querySms(phone,type);
    }

    @Override
    public int updateSms(Long id, Sms sms) {
        return smsMapper.updateSms(id,sms);
    }
}
