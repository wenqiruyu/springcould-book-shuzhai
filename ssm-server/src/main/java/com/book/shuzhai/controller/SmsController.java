package com.book.shuzhai.controller;

import com.book.shuzhai.entity.SmsConfig;
import com.book.shuzhai.service.ISmsService;
import com.book.shuzhai.utils.SmsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "短信服务sms-server")
public class SmsController {

    @Autowired
    private ISmsService smsService;


    @RequestMapping(value = "/sms/register", method = RequestMethod.GET)
    @ApiOperation(value = "注册验证短信", notes = "根据用户填写的手机号码发送给用户验证码")
    @ApiImplicitParam(name = "phone", value = "注册短信验证电话号码phone", required = true, dataType = "String")
    public String toVerifyRegister(@RequestParam("phone") String phone) {
        SmsConfig smsConfig = smsService.querySmsConfig("榛子短信");
        if (smsConfig != null) {
            return smsService.sendSms(phone, smsConfig, SmsTemplate.getRegisterSms());
        }
        return null;
    }

    @RequestMapping(value = "/sms/info", method = RequestMethod.GET)
    @ApiOperation(value = "修改密码验证短信", notes = "根据用户填写的手机号码发送给用户验证码")
    @ApiImplicitParam(name = "phone", value = "注册短信验证电话号码phone", required = true, dataType = "String")
    public String toVerifyUpdatePwd(@RequestParam("phone") String phone) {
        SmsConfig smsConfig = smsService.querySmsConfig("榛子短信");
        if (smsConfig != null) {
            return smsService.sendSms(phone, smsConfig, SmsTemplate.getUpdatePwdSms());
        }
        return null;
    }

    @GetMapping("/sms/config/{name}")
    @ApiOperation(value = "获取短信相关配置", notes = "根据短信验证码运营商名称获取短信相关配置")
    @ApiImplicitParam(name = "name", value = "短信验证运营商名称", required = true, dataType = "String")
    public SmsConfig getSmsConfig(@PathVariable("name") String name) {
        return smsService.querySmsConfig(name);
    }
}
