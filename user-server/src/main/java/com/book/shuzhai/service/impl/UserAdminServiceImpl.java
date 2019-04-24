package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.UserAdmin;
import com.book.shuzhai.mapper.UserAdminMapper;
import com.book.shuzhai.service.IUserAdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminServiceImpl implements IUserAdminService {

    @Autowired
    private UserAdminMapper userAdminMapper;

    @Override
    public int addAdmin(UserAdmin userAdmin) {
        if(userAdmin == null){
            return 0;
        }
        return userAdminMapper.insertAdmin(userAdmin);
    }

    @Override
    public UserAdmin getAdminByName(String username) {
        if(StringUtils.isBlank(username)){
            return null;
        }
        return userAdminMapper.queryAdminByName(username);
    }
}
