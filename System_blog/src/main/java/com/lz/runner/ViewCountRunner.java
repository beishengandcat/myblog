package com.lz.runner;

import com.lz.domain.Article;
import com.lz.mapper.ArticleMapper;
import com.lz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：程序启动时会做的操作，将文章id和对应的浏览量存入redis
 *
 * @Author: 李振
 * @Date: 2022/12/22 22:06
 **/
@Component
public class ViewCountRunner implements CommandLineRunner {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);

        Map<String, Integer> viewCountMap = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }

}
