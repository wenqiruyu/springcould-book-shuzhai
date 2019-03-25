package com.book.shuzhai.mapper;


import com.book.shuzhai.entity.User;

public interface UserMapper {

    int insertUser(User user);

    User queryUserById(Long id);

    User queryUserByName(String username);

    String queryPasswordByName(String username);

    User queryUserByPhone(String phone);
}
