package com.book.shuzhai.service;

import com.book.shuzhai.entity.Comment;
import com.book.shuzhai.entity.Product;

import java.util.List;

public interface IProductService {

    int addProduct(Product product);

    List<Product> getProductList();

    Product getProductById(Long id);

    List<Product> getProductListByCategory(Long categoryId);

    List<Product> getProductBySearch(String search);

    int addComment(Comment comment);

    List<Comment> getCommentByProduct(Long productId);
}
