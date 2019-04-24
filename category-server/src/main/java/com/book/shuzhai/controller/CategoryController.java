package com.book.shuzhai.controller;

import com.alibaba.fastjson.JSON;
import com.book.shuzhai.entity.Category;
import com.book.shuzhai.feign.RedisFeignClient;
import com.book.shuzhai.service.ICategoryService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "分类服务category-server")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RedisFeignClient redisFeignClient;

    @GetMapping("/category")
    @CrossOrigin
    @ApiOperation(value = "图书分类", notes = "图书分类列表")
    public ServerResponse<List<Category>> getCategoryList(){
        String jsonCategory = redisFeignClient.toGetJsonList("category-list");
        List<Category> categories = JSON.parseArray(jsonCategory, Category.class);
        if(categories != null){
            return ServerResponse.createBySuccess(categories);
        }
        List<Category> categoryList = categoryService.getCategoryList();
        if(categoryList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        redisFeignClient.toSaveJsonList("category-list",JSON.toJSON(categoryList).toString());
        return ServerResponse.createBySuccess(categoryList);
    }
}
