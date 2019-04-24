package com.book.shuzhai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 后台管理实体类，用于给商家管理员使用
 */
@Data
public class UserAdmin {

    private Long id;
    private String username;
    private String password;
    // 管理员角色  0为普通管理员 1为高级管理员
    private Integer role;
    // 管理员头像
    private String face;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public UserAdmin(){}

    public UserAdmin(String username, String password, Integer role, String face, Date createTime) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.face = face;
        this.createTime = createTime;
    }

    public UserAdmin(Long id, String username, String password, Integer role, String face, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.face = face;
        this.createTime = createTime;
    }
}
