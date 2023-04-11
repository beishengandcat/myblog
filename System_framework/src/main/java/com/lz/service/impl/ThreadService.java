package com.lz.service.impl;

import com.lz.domain.Article;
import com.lz.mapper.ArticleMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    @Async
    public void updateViewCountByThreadPool(ArticleMapper baseMapper, Article article) {

        Long viewCount = article.getViewCount();
        Long aLong = new Long(1L);

        article.setViewCount( viewCount + aLong );

//        queryWrapper.eq(Article::getViewCount,article.getViewCount());

//            articleService.update(updateWrapper);
        baseMapper.updateById(article);
        try {
            //睡眠5秒 证明不会影响主线程的使用
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
