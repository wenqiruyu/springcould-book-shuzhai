package com.book.shuzhai.controller;

import com.book.shuzhai.entity.Sms;
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

    @RequestMapping(value = "/sms",method = RequestMethod.GET)
    @ApiOperation(value = "注册验证短信", notes = "根据用户填写的手机号码发送给用户验证码")
    @ApiImplicitParam(name = "phone", value = "短信验证电话号码phone", required = true, dataType = "Long")
    public String toVerifyRegister(@RequestParam("phone") String phone){
        SmsConfig smsConfig = smsService.querySmsConfig("榛子短信");
        if(smsConfig != null){
            int result = smsService.sendSms(phone, smsConfig, SmsTemplate.getRegisterSms());
            if(result == 1){
                return "短信发送成功，请查收";
            }
        }
        return "系统错误，请稍后再试";
    }

    @GetMapping("/sms/verify")
    @ApiOperation(value = "获取注册码", notes = "根据用户填写的手机号码和短信类型获取相对的验证码")
    @ApiImplicitParam(name = "phone", value = "手机号码phone", required = true, dataType = "Long")
    public String getSmsVerify(@RequestParam("phone") String phone,@RequestParam("type")String type){
        Sms sms = smsService.getSms(phone, Integer.parseInt(type));
        if(sms != null){
            return sms.getVerifyCode();
        }
        return null;
    }

    @GetMapping("/sms/config/{name}")
    @ApiOperation(value = "获取短信相关配置",notes = "根据短信验证码运营商名称获取短信相关配置")
    @ApiImplicitParam(name = "name", value = "短信验证运营商名称",required = true, dataType = "String")
    public SmsConfig getSmsConfig(@PathVariable("name") String name){
        return smsService.querySmsConfig(name);
    }
}
