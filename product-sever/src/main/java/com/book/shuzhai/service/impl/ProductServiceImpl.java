package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.Product;
import com.book.shuzhai.mapper.ProductMapper;
import com.book.shuzhai.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductList() {
        return productMapper.queryProductList();
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.queryProductById(id);
    }

    @Override
    public List<Product> getProductListByCategory(Long categoryId) {
        return productMapper.queryProductListByCategory(categoryId);
    }
}
