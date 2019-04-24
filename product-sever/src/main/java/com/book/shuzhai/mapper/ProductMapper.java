package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Comment;
import com.book.shuzhai.entity.Product;

import java.util.List;

public interface ProductMapper {

    int insertProduct(Product product);

    List<Product> queryProductList();

    Product queryProductById(Long id);

    List<Product> queryProductListByCategory(Long categoryId);

    List<Product> queryProductListBySearch(String search);

    int insertComment(Comment comment);

    List<Comment> queryCommentByProduct(Long productId);
}