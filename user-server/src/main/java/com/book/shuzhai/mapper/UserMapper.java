package com.book.shuzhai.mapper;


import com.book.shuzhai.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> queryAllUser();

    int insertUser(User user);

    User queryUserById(Long id);

    User queryUserByName(String username);

    String queryPasswordByName(String username);

    User queryUserByPhone(String phone);

    int updateUser(User user);
}
