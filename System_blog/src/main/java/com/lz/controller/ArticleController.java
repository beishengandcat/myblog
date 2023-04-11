package com.lz.controller;


import com.lz.annotation.SystemLog;
import com.lz.domain.ResponseResult;
import com.lz.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：文章操作
 *
 * @Author: 李振
 * @Date: 2022/12/14 18:13
 **/
@RestController
@RequestMapping("/article")
@Api(tags = "文章",description = "文章相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //查询所有的热门文章
    @GetMapping("/hotArticleList")
    @SystemLog(businessName = "查询热门文章信息")
    @ApiOperation(value = "查询热门文章列表")
    public ResponseResult hotArticleList() {
        //查询热门文章,封装成ResponseResult返回
        return articleService.hotArticleList();
    }

    //分页查询所有的文章
    @GetMapping("/articleList")
    @SystemLog(businessName = "查询所有的文章")
    @ApiOperation(value = "查询所有文章")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    //根据文章id查询文章详情
    @GetMapping("/{id}")
    @SystemLog(businessName = "根据文章id查询文章详情")
    @ApiOperation(value = "根据id查询文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    //修改文章的访问量
    @PutMapping("/updateViewCount/{id}")
    @SystemLog(businessName = "修改文章的访问量")
    @ApiOperation(value = "修改文章的访问量")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }



}
