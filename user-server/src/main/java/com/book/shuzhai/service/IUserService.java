package com.book.shuzhai.service;

import com.book.shuzhai.entity.User;
import com.book.shuzhai.utils.ServerResponse;

import java.util.List;

public interface IUserService {

    List<User> getAllUser();

    int addUser(User user);

    User getUserById(Long id);

    User getUserByName(String username);

    String getPasswordByName(String username);

    User getUserByPhone(String phone);

    int updateUser(User user);

    ServerResponse<String> userLogin(String name, String password);

    ServerResponse<String> userRegister(User user);

    int updateUserPassword(User user);
}
