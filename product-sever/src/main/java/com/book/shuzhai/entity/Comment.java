package com.book.shuzhai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Long id;
    private Long userId;
    private Long productId;
    private Long likeNum;
    private String detail;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private User user;

    public Comment() {
    }

    public Comment(Long userId, Long productId, String detail, Date createTime) {
        this.userId = userId;
        this.productId = productId;
        this.detail = detail;
        this.createTime = createTime;
    }

    public Comment(Long userId, Long productId, Long likeNum, String detail, Integer status, Date createTime) {
        this.userId = userId;
        this.productId = productId;
        this.likeNum = likeNum;
        this.detail = detail;
        this.status = status;
        this.createTime = createTime;
    }

    public Comment(Long id, Long userId, Long productId, Long likeNum, String detail, Integer status, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.likeNum = likeNum;
        this.detail = detail;
        this.status = status;
        this.createTime = createTime;
    }

    public Comment(Long id, Long userId, Long productId, Long likeNum, String detail, Integer status, Date createTime, User user) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.likeNum = likeNum;
        this.detail = detail;
        this.status = status;
        this.createTime = createTime;
        this.user = user;
    }
}
