package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.domain.ResponseResult;
import com.lz.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：文章类型
 *
 * @Author: 李振
 * @Date: 2022/12/14 21:27
 **/
@RestController
@RequestMapping("/category")
@Api(tags = "文章类型",description = "文章类型相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @SystemLog(businessName = "查询所有的文章类型")
    @ApiOperation(value = "查询所有的文章类型")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
