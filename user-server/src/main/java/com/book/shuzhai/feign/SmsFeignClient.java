package com.book.shuzhai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sms-server")
public interface SmsFeignClient {

    @RequestMapping(value = "/sms/register", method = RequestMethod.GET)
    String toVerifyRegister(@RequestParam("phone") String phone);

    @RequestMapping(value = "/sms/info", method = RequestMethod.GET)
    String toVerifyUpdatePwd(@RequestParam("phone") String phone);
}
