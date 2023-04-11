package com.lz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.Comment;
import com.lz.domain.ResponseResult;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-12-19 20:10:05
 */
public interface CommentService extends IService<Comment> {

    //查询所有的评论信息
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    //添加评论
    ResponseResult addComment(Comment comment);
}

