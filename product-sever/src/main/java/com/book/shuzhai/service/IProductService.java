package com.book.shuzhai.service;

import com.book.shuzhai.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> getProductList();

    Product getProductById(Long id);

    List<Product> getProductListByCategory(Long categoryId);


}
