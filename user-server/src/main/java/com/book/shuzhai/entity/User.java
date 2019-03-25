package com.book.shuzhai.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    // 用户角色  1为普通用户，0为管理员  可以考虑添加会员扩展
    private Integer role;
    // 用户性别  0为保密（默认）,1为男，2为女
    private Integer sex;
    // 用户头像，存储的是图片地址，新建用户使用默认头像
    private String face;
    // 设置时间戳 createTime创建时间  updateTime最后一次更改时间
    private Date createTime;
    private Date updateTime;

    public User(){}

    public User(Long id,String username){
        this.id = id;
        this.username = username;
    }

    public User(String username, String password, String email, String phone, Integer role, Integer sex, String face, Date createTime, Date updateTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.sex = sex;
        this.face = face;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
