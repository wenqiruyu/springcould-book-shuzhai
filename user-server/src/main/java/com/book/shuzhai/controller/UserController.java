package com.book.shuzhai.controller;

import com.alibaba.fastjson.JSON;
import com.book.shuzhai.entity.User;
import com.book.shuzhai.feign.RedisFeignClient;
import com.book.shuzhai.feign.SmsFeignClient;
import com.book.shuzhai.service.IUserService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private SmsFeignClient smsFeignClient;

    @Autowired
    private RedisFeignClient redisFeignClient;

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/user/list")
    @CrossOrigin
    @ApiOperation(value = "获取全部用户", notes = "获取用户列表")
    public ServerResponse<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        if(allUser == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试！");
        }
        return ServerResponse.createBySuccess(allUser);
    }

    @PostMapping("/user/register")
    @CrossOrigin
    @ApiOperation(value = "用户注册", notes = "用户的注册功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "name"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "password"),
            @ApiImplicitParam(name = "number", value = "手机号", required = true, dataType = "number"),
            @ApiImplicitParam(name = "verify", value = "验证码", required = true, dataType = "verify")
    })
    public ServerResponse<String> userRegister(@RequestBody Map<String, String> map) {
        // 获取redis中的相对应的验证码
        String verify = redisFeignClient.toGetString(map.get("phone"));
        System.out.println(verify);
        // 获取数据库中存储的验证码
        if (map.get("verify").equals(verify)) {
            User user =
                    new User(map.get("name"), map.get("password"), null, null, map.get("phone"), role, sex, face, null, null, null, new Date(), new Date());
            return userService.userRegister(user);
        }
        return ServerResponse.createByErrorMessage("验证码错误哦");
    }

    @PostMapping("/user/login")
    @CrossOrigin
    @ApiOperation(value = "用户登录", notes = "用户的登录功能")
    @ApiImplicitParam(name = "user", value = "用户姓名、密码对象", required = true, dataType = "User")
    public ServerResponse userLogin(@RequestParam(value = "username", required = true) String username,
                                     @RequestParam(value = "password", required = true) String password) {
        ServerResponse<String> userServerResponse = userService.userLogin(username, password);
        // 查询用户存在是否，考虑session
        if(userServerResponse.isSuccess()){
            // 将用户信息存储进redis中 key使用token
            String data = userServerResponse.getData();
            String userToken = UUID.randomUUID().toString();
            redisFeignClient.toSaveHash("user-login", userToken, data);
            return ServerResponse.createBySuccess(userToken);
        }
        return userServerResponse;
    }

    @GetMapping("/user/info")
    @CrossOrigin
    @ApiOperation(value = "根据token获取用户信息", notes = "获取缓存中用户信息")
    @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String")
    public ServerResponse getUserByToken(@RequestParam(value = "token", required = true) String token) {
        String user = (String) stringRedisTemplate.opsForHash().get("user-login",token);
        if(user == null){
            return ServerResponse.createByErrorMessage("凭据过期了哦");
        }
        return ServerResponse.createBySuccess(JSON.parse(user));
    }

    @DeleteMapping("/user")
    @CrossOrigin
    @ApiOperation(value = "删除缓存中用户信息", notes = "根据token删除缓存中用户信息")
    @ApiImplicitParam(name = "token", value = "用户token", required = true, dataType = "String")
    public ServerResponse toDelByToken(@RequestParam(value = "token", required = true) String token) {
        System.out.println(token);
        Long result = redisFeignClient.toDeleteHash("user-login", token);
        System.out.println(result);
        if(result != 1){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess();
    }

    @GetMapping("/user/sms")
    @CrossOrigin
    @ApiOperation(value = "发送验证码", notes = "给用户注册的手机号发送短信验证码")
    @ApiImplicitParam(name = "phone", value = "用户注册手机号", required = true, dataType = "String")
    public ServerResponse<String> sendVerifySms(@RequestParam("phone") String phone) {
        // 先判定手机号相对应的短信类型在数据库是否存在
        if (userService.getUserByPhone(phone) == null) {
            // 进行注册短信的发送 返回验证码
            String verifyCode = smsFeignClient.toVerifyRegister(phone);
            if (verifyCode == null) {
                return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
            }
            // 短信发送成功，进行对短信验证码的redis存储
            redisFeignClient.toSaveString(phone, Long.parseLong("15"), verifyCode);
            return ServerResponse.createBySuccessMessage("验证短信发送成功");
        }
        return ServerResponse.createByErrorCodeMessage(8, "该手机号已经注册过啦");
    }

    @GetMapping("/user/sms/updatePwd")
    @CrossOrigin
    @ApiOperation(value = "修改密码手机验证码", notes = "给用户注册的手机号发送短信验证码")
    @ApiImplicitParam(name = "phone", value = "用户注册手机号", required = true, dataType = "String")
    public ServerResponse<String> sendVerifySmsToUpdate(@RequestParam("phone") String phone) {
        // 先判定手机号相对应的短信类型在数据库是否存在
        String verify= smsFeignClient.toVerifyUpdatePwd(phone);
        if (verify == null) {
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        // 将修改信息的验证码进行redis存储,防止和注册混淆，进行key值的区分
        String stringKey = "update:" + phone;
        redisFeignClient.toSaveString(stringKey,Long.parseLong("15"), verify);
        return ServerResponse.createBySuccessMessage("验证短信发送成功");
    }

    @GetMapping("/user/updatePwd")
    @CrossOrigin
    @ApiOperation(value = "验证修改密码操作", notes = "验证用户输入的验证码是否匹配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "verify", value = "验证码", required = true, dataType = "String")
    })
    public ServerResponse<String> verifySmsToUpdate(@RequestParam("phone") String phone, @RequestParam("verify") String verify) {
        String stringKey = "update:" + phone;
        // 根据手机号获取redis中的验证码，进行比对
        if(verify.equals(redisFeignClient.toGetString(stringKey))){
            return ServerResponse.createBySuccessMessage("验证码匹配");
        }
        return ServerResponse.createByErrorMessage("验证码不匹配哦");
    }

    @GetMapping("/user/register")
    @CrossOrigin
    @ApiOperation(value = "验证用户名", notes = "用户名不能重复")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public ServerResponse<String> verifyName(@RequestParam("username") String username) {
        User user = userService.getUserByName(username);
        if (user == null) {
            return ServerResponse.createBySuccessMessage("用户名不存在，允许注册哦");
        }
        return ServerResponse.createByErrorMessage("用户名已经存在，请更换用户名");
    }

    @GetMapping("/user/{id}")
    @CrossOrigin
    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    public ServerResponse<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ServerResponse.createBySuccess(user);
    }

    @PutMapping("/user/password")
    @CrossOrigin
    @ApiOperation(value = "修改用户密码", notes = "实现用户的更改密码操作")
    @ApiImplicitParam(name = "user", value = "用户id和用户新密码password", required = true, dataType = "User")
    public ServerResponse<String> updateUserPassword(@RequestBody User user) {
        user.setUpdateTime(new Date());
        int result = userService.updateUserPassword(user);
        if (result == 1) {
            return ServerResponse.createBySuccess("密码修改成功成功哦");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @PutMapping("/user")
    @CrossOrigin
    @ApiOperation(value = "更新用户信息", notes = "实现用户的更改信息操作")
    @ApiImplicitParam(name = "user", value = "用户信息user", required = true, dataType = "User")
    public ServerResponse<String> updateUserInfo(@RequestBody User user) {
        user.setUpdateTime(new Date());
        int result = userService.updateUser(user);
        if (result == 1) {
            return ServerResponse.createBySuccess("信息修改成功哦");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }
}
