package com.book.shuzhai.controller;

import com.book.shuzhai.entity.User;
import com.book.shuzhai.feign.SmsFeignClient;
import com.book.shuzhai.service.IUserService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@Api(tags = "用户服务user-server")
public class UserController {

    @Value("${role}")
    private Integer role;
    @Value("${sex}")
    private Integer sex;
    @Value("${face}")
    private String face;

    @Autowired
    private IUserService userService;

    @PostMapping("/user/register")
    @CrossOrigin
    @ApiOperation(value = "用户注册",notes = "用户的注册功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名",required = true,dataType = "name"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "password"),
            @ApiImplicitParam(name = "number",value = "手机号",required = true,dataType = "number"),
            @ApiImplicitParam(name = "verify",value = "验证码",required = true,dataType = "verify")
    })
    public ServerResponse<String> userRegister(@RequestBody Map<String,String> map){
        System.out.println(smsFeignClient.getSmsVerify(map.get("phone"), "0"));
        // 获取数据库中存储的验证码
        if(map.get("verify").equals(smsFeignClient.getSmsVerify(map.get("phone"), "0"))){
            return userService.userRegister(new User(map.get("name"),map.get("password"),null,map.get("phone"),role,sex,face,new Date(),new Date()));
        }
        return ServerResponse.createByErrorMessage("验证码错误哦");
    }

    @Autowired
    private SmsFeignClient smsFeignClient;

    @PostMapping("/user/login")
    @CrossOrigin
    @ApiOperation(value = "用户登录",notes = "用户的登录功能")
    @ApiImplicitParam(name = "user",value = "用户姓名、密码对象",required = true,dataType = "User")
    public ServerResponse<User> userLogin(@RequestBody User user){
        System.out.println(user.toString());
        ServerResponse<User> userServerResponse = userService.userLogin(user.getUsername(), user.getPassword());
        // 查询用户存在是否，考虑session
        return userServerResponse;
    }

    @GetMapping("/user/sms")
    @CrossOrigin
    @ApiOperation(value = "发送验证码",notes = "给用户注册的手机号发送短信验证码")
    @ApiImplicitParam(name = "phone",value = "用户注册手机号",required = true,dataType = "String")
    public ServerResponse<String> sendVerifySms(@RequestParam("phone") String phone){
        // 先判定手机号相对应的短信类型在数据库是否存在
        if(userService.getUserByPhone(phone) == null){
            String result = smsFeignClient.toVerifyRegister(phone);
            if(result == null){
                return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
            }
            return ServerResponse.createBySuccessMessage("验证短信发送成功");
        }
        return ServerResponse.createByErrorCodeMessage(8,"该手机号已经注册过啦");
    }

    @GetMapping("/user/register")
    @CrossOrigin
    @ApiOperation(value = "验证用户名",notes = "用户名不能重复")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String")
    public ServerResponse<String> verifyName(@RequestParam("username") String username){
        User user = userService.getUserByName(username);
        if(user == null){
            return ServerResponse.createBySuccessMessage("用户名不存在，允许注册哦");
        }
        return ServerResponse.createByErrorMessage("用户名已经存在，请更换用户名");
    }

    @GetMapping("/user/{id}")
    @CrossOrigin
    @ApiOperation(value = "获取用户信息",notes = "根据用户id获取用户详细信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")
    public ServerResponse<User> getUserById(@PathVariable("id") Long id){
        return null;
    }


}
