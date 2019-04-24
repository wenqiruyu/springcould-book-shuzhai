package com.book.shuzhai.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 短信的公共模板类
 */
public class SmsTemplate {

    /**
     * 生成六位验证码
     * @return
     */
    public static String getVerifyCode(){
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

    /**
     * 用户进行注册的时候的验证码短信
     * @return
     */
    public static Map<String,String> getRegisterSms(){
        Map<String,String> map = new HashMap<>();
        String verifyCode = getVerifyCode();
        String verifyDetail = "您正在注册书斋网，手机验证码为:" + verifyCode + "，请在15分钟内使用。（请确保是本人操作且为本人手机，否则请忽略此短信）";
        map.put("verifyCode",verifyCode);
        map.put("verifyDetail",verifyDetail);
        return map;
    }

    /**
     * 用户进行手机号验证码登录的短信
     * @return
     */
    public static Map<String,String> getLoginSms(){
        Map<String,String> map = new HashMap<>();
        String verifyCode = getVerifyCode();
        String verifyDetail = "您的登录验证码为" + verifyCode + "。您正在使用短信验证码登录功能，该验证码仅用于身份验证，请勿泄漏给他人。";
        map.put("verifyCode",verifyCode);
        map.put("verifyDetail",verifyDetail);
        return map;
    }

    /**
     * 用户进行手机号更改密码的验证短信
     * @return
     */
    public static Map<String,String> getUpdatePwdSms(){
        Map<String,String> map = new HashMap<>();
        String verifyCode = getVerifyCode();
        String verifyDetail = "您正在修改密码！验证码为" + verifyCode + "。该验证码仅用于身份验证，请勿泄漏给他人。（请确保是本人操作且为本人手机，否则请忽略此短信）";
        map.put("verifyCode",verifyCode);
        map.put("verifyDetail",verifyDetail);
        return map;
    }
}
