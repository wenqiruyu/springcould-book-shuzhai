package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.UserAdmin;

public interface UserAdminMapper {

    int insertAdmin(UserAdmin userAdmin);

    UserAdmin queryAdminByName(String username);
}
