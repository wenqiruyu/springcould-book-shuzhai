package com.book.shuzhai.service;

import com.book.shuzhai.entity.UserAdmin;

public interface IUserAdminService {

    int addAdmin(UserAdmin userAdmin);

    UserAdmin getAdminByName(String username);
}
