package com.book.shuzhai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    // 商品所属分类id
    private Long categoryId;
    private String name;
    // 书作者
    private String author;
    // 副标题
    private String subtitle;
    // 出版社
    private String press;
    // 出版日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    // 商品主图
    private String mainImg;
    // 商品图片 json格式存储
    private String subImg;
    // 商品subImg相对应的缩略图
    private String thumbnail;
    // 商品详情描述
    private String detail;
    // 商品定价
    private BigDecimal pricing;
    private BigDecimal price;
    // 商品数量
    private Long stock;
    // 商品评分 默认为0
    private Long grade;
    // 商品用户评论数 默认为0
    private Long commentNum;
    // 商品销量
    private Long sale;
    // 商品状态 0删除  1下架  2上架   3热卖
    private Integer status;
}
