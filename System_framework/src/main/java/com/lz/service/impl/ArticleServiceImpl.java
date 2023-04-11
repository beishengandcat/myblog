package com.lz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.constants.SysConstants;
import com.lz.domain.*;
import com.lz.domain.dto.AddArticleDto;
import com.lz.domain.vo.*;
import com.lz.mapper.ArticleMapper;
import com.lz.service.ArticleService;
import com.lz.service.ArticleTagService;
import com.lz.service.CategoryService;
import com.lz.utils.BeanCopyUtils;
import com.lz.utils.RedisCache;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 功能描述：文章
 *
 * @Author: 李振
 * @Date: 2022/12/14 18:11
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章，封装成ResponseResult对象返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SysConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查出十条信息
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();

//        //启用封装类
//        List<HotArticleVo> articleVos = new ArrayList<>();

        //bean拷贝
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(vs);
    }


    //分页查询所有的文章列表
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //在首页和分类也都需要查询文章列表
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId 就要查询,要和传入的categoryId相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SysConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序实现置顶的文章放在最前面,这里的操作是展示置顶的文章
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

        //查询CategoryName
        List<Article> articles = page.getRecords();
        //articleId去查询articleName进行设置
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }



    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }


    //更新访问量
    @Override
    public ResponseResult updateViewCount(Long id) {
        /**
         * 方案1： 程序启动从数据库中取出id对应的访问量存储到redis，使用定时任务处理redis中的数据，将redis中的数据定期修改到数据库
         * 方案2： 使用线程池异步调用数据库，实现修改访问量
         */
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);



//        第二种方案：使用线程池
//        Article article = baseMapper.selectById(id);
//        threadService.updateViewCountByThreadPool(articleMapper,article);

        return ResponseResult.okResult();
    }


    //添加博文
    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }


    //查询文章列表
    @Override
    public PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(article.getTitle()),Article::getTitle, article.getTitle());
        queryWrapper.like(StringUtils.hasText(article.getSummary()),Article::getSummary, article.getSummary());

        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);


        List<Article> articles = page.getRecords();
        //转换成VO
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, ArticleVo.class);

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(articleVos);
        return pageVo;
    }


    @Override
    public ArticleVo getInfo(Long id) {
        Article article = getById(id);
        //获取关联标签
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        List<ArticleTag> articleTags = articleTagService.list(articleTagLambdaQueryWrapper);
        List<Long> tags = articleTags.stream().map(articleTag -> articleTag.getTagId()).collect(Collectors.toList());

        ArticleVo articleVo = BeanCopyUtils.copyBean(article,ArticleVo.class);
        articleVo.setTags(tags);
        return articleVo;
    }


    @Override
    public void edit(AddArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        //更新博客信息
        updateById(article);
        //删除原有的 标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        //添加新的博客和标签的关联信息
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(articleDto.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
    }


}
