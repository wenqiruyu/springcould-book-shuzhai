package com.book.shuzhai.service;

import com.book.shuzhai.entity.User;
import com.book.shuzhai.utils.ServerResponse;

public interface IUserService {

    int addUser(User user);

    User getUserById(Long id);

    User getUserByName(String username);

    String getPasswordByName(String username);

    User getUserByPhone(String phone);

    ServerResponse<User> userLogin(String name, String password);

    ServerResponse<String> userRegister(User user);
}
