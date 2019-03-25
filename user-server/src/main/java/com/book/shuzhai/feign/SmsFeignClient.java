package com.book.shuzhai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sms-server")
public interface SmsFeignClient {

    @GetMapping(value = "/sms")
    String toVerifyRegister(@RequestParam("phone") String phone);

    @GetMapping(value = "/sms/verify")
    String getSmsVerify(@RequestParam("phone")String phone,@RequestParam("type")String type);
}
