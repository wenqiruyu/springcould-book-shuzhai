package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.User;
import com.book.shuzhai.mapper.UserMapper;
import com.book.shuzhai.service.IUserService;
import com.book.shuzhai.utils.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

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
    public ServerResponse<User> userLogin(String username, String password) {
        if(!(StringUtils.isBlank(username) || StringUtils.isBlank(password))){
            String pwd = userMapper.queryPasswordByName(username);
            if(pwd == null){
                return ServerResponse.createByErrorMessage("用户名未注册");
            }else{
                if(!password.equals(pwd)){
                    return ServerResponse.createByErrorMessage("密码不正确");
                }
                User user = userMapper.queryUserByName(username);
                return ServerResponse.createBySuccess(new User(user.getId(),user.getUsername()));
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @Override
    public ServerResponse<String> userRegister(User user) {
        int result = userMapper.insertUser(user);
        if(result == 1){
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }
}
