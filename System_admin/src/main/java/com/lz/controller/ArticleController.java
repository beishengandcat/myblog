package com.lz.controller;

import com.lz.domain.Article;
import com.lz.domain.ResponseResult;
import com.lz.domain.dto.AddArticleDto;
import com.lz.domain.vo.ArticleVo;
import com.lz.domain.vo.PageVo;
import com.lz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：新增博文
 *
 * @Author: 李振
 * @Date: 2023/1/11 15:10
 **/
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, Article article){
        PageVo pageVo = articleService.selectArticlePage(article,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }

    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        ArticleVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    @PutMapping
    public ResponseResult edit(@RequestBody AddArticleDto articleDto){
        articleService.edit(articleDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}

