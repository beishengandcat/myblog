package com.lz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述：文章详情
 *
 * @Author: 李振
 * @Date: 2022/12/15 10:42
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {

    private Long id;

    //标题
    private String title;

    //文章摘要
    private String summary;

    //文章内容
    private String content;

    //所属分类id
    private Long categoryId;

    //所属分类名
    private String categoryName;

    //缩略图
    private String thumbnail;

    //访问量
    private Long viewCount;

    //创建时间
    private Date createTime;
}
