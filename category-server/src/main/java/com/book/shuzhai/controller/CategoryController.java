package com.book.shuzhai.controller;

import com.book.shuzhai.entity.Category;
import com.book.shuzhai.service.ICategoryService;
import com.book.shuzhai.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category")
    @CrossOrigin
    public ServerResponse<List<Category>> getCategoryList(){
        List<Category> categoryList = categoryService.getCategoryList();
        if(categoryList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(categoryList);
    }
}
