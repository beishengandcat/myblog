package com.lz.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lz.domain.Article;
import com.lz.service.ArticleService;
import com.lz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：定时任务
 *
 * @Author: 李振
 * @Date: 2022/12/22 22:29
 **/
@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    /**
     * 每三分钟会修改redis中的浏览量
     */
//    @Scheduled(cron = "0 0/3 * * * ? ")

    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        List<Article> articles = viewCountMap.entrySet().stream().map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

//        //存入数据库中
        articleService.updateBatchById(articles);
        for (Article article : articles) {
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article :: getId, article.getId());
            updateWrapper.set(Article :: getViewCount, article.getViewCount());
            articleService.update(updateWrapper);
        }
    }
}
