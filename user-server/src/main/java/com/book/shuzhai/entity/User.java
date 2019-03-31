package com.book.shuzhai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    // 用户使用qq第三方登录
    private String qq;
    private String phone;
    // 用户角色  1为普通用户，0为管理员  可以考虑添加会员扩展
    private Integer role;
    // 用户性别  0为保密（默认）,1为男，2为女
    private Integer sex;
    // 用户头像，存储的是图片地址，新建用户使用默认头像
    private String face;
    // 出生日期 生日
    private String birthday;
    // 签名
    private String signature;
    // 密保问题,使用json格式存储
    private String question;
    // 设置时间戳 createTime创建时间  updateTime最后一次更改时间
    private Date createTime;
    private Date updateTime;

    public User(){}

    public User(Long id,String username){
        this.id = id;
        this.username = username;
    }

    public User(String username, String password, String email, String qq, String phone, Integer role, Integer sex, String face, String birthday, String signature, String question, Date createTime, Date updateTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.qq = qq;
        this.phone = phone;
        this.role = role;
        this.sex = sex;
        this.face = face;
        this.birthday = birthday;
        this.signature = signature;
        this.question = question;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
