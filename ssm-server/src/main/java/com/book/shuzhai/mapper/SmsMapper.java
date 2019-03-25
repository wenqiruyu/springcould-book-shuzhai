package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Sms;

public interface SmsMapper {

    int insertSms(Sms sms);

    Sms querySms(String phone,Integer type);

    int updateSms(Long id,Sms sms);
}
