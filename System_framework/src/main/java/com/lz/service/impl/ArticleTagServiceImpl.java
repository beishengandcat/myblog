package com.lz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.domain.ArticleTag;
import com.lz.mapper.ArticleTagMapper;
import com.lz.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-01-11 15:15:48
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

