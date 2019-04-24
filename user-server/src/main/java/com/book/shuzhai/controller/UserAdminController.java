package com.book.shuzhai.controller;

import com.book.shuzhai.entity.UserAdmin;
import com.book.shuzhai.service.IUserAdminService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "后台管理系统user-server")
public class UserAdminController {

    @Autowired
    private IUserAdminService userAdminService;

    @PostMapping("/user/admin")
    @CrossOrigin
    @ApiOperation(value = "后台管理系统登录", notes = "书斋在线书店后台管理系统的登录模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
    })
    public ServerResponse<String> login(@RequestParam String username, @RequestParam String password){
        UserAdmin userAdmin = userAdminService.getAdminByName(username);
        if(userAdmin == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试！");
        }else if(password != null && password.equals(userAdmin.getPassword())){
            return ServerResponse.createBySuccessMessage("用户名密码验证正确，登录成功！");
        }else{
            return ServerResponse.createByErrorMessage("用户名或密码错误！");
        }
    }
}
