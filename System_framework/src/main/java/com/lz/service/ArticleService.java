package com.lz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.Article;
import com.lz.domain.ResponseResult;
import com.lz.domain.dto.AddArticleDto;
import com.lz.domain.vo.ArticleVo;
import com.lz.domain.vo.PageVo;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/14 18:10
 **/
public interface ArticleService extends IService<Article> {

    //查询热门文章
    ResponseResult hotArticleList();

    //查询所有的文章
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    //查看文章内容
    ResponseResult getArticleDetail(Long id);

    //更新redis中的浏览量
    ResponseResult updateViewCount(Long id);

    //添加博文信息
    ResponseResult add(AddArticleDto article);

    //查询文章列表
    PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize);

    //根据id查询对应的文章信息
    ArticleVo getInfo(Long id);

    //修改文章
    void edit(AddArticleDto articleDto);


}
