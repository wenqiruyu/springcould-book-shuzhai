package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> queryProductList();

    Product queryProductById(Long id);

    List<Product> queryProductListByCategory(Long categoryId);
}