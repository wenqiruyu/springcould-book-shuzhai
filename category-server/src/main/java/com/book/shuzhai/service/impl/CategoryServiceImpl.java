package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.Category;
import com.book.shuzhai.mapper.CategoryMapper;
import com.book.shuzhai.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.queryCategoryList();
    }
}
