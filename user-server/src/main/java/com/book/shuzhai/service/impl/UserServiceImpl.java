package com.book.shuzhai.service.impl;

import com.alibaba.fastjson.JSON;
import com.book.shuzhai.entity.User;
import com.book.shuzhai.feign.RedisFeignClient;
import com.book.shuzhai.mapper.UserMapper;
import com.book.shuzhai.service.IUserService;
import com.book.shuzhai.utils.MD5Util;
import com.book.shuzhai.utils.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisFeignClient redisFeignClient;

    @Override
    public List<User> getAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.queryUserByName(username);
    }

    @Override
    public String getPasswordByName(String username) {
        return userMapper.queryPasswordByName(username);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.queryUserByPhone(phone);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public ServerResponse<String> userLogin(String username, String password) {
        if(!(StringUtils.isBlank(username) || StringUtils.isBlank(password))){
            String pwd = userMapper.queryPasswordByName(username);
            if(pwd == null){
                return ServerResponse.createByErrorMessage("用户名未注册");
            }else{
                if(!MD5Util.MD5EncodeUtf8(password).equals(pwd)){
                    return ServerResponse.createByErrorMessage("用户名或密码不正确");
                }
                User user = userMapper.queryUserByName(username);
                Map<String, Object> map = new HashMap<>();
                return ServerResponse.createBySuccess(JSON.toJSONString(new User(user.getId(),user.getUsername())));
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @Override
    public ServerResponse<String> userRegister(User user) {
        // 需要对password进行MD5并加盐salt加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int result = userMapper.insertUser(user);
        if(result == 1){
            // 注册成功，将redis中的验证码删除, 就是将该redis的值设置为 ''
            redisFeignClient.toSaveString(user.getPhone(), Long.parseLong("15"),"");
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @Override
    public int updateUserPassword(User user) {
        // 将密码进行加盐MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        return userMapper.updateUser(user);
    }
}
