package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.constants.SysConstants;
import com.lz.domain.Comment;
import com.lz.domain.ResponseResult;
import com.lz.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：评论信息操作
 *
 * @Author: 李振
 * @Date: 2022/12/19 20:12
 **/
@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SysConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    @SystemLog(businessName = "添加评论")
    @ApiOperation(value = "添加评论")
    public ResponseResult addComment(@RequestBody Comment comment){
       return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "查询所有的友链评论")
    @ApiOperation(value = "查询友链评论列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum",value = "页号"), @ApiImplicitParam(name = "pageSize",value = "每页大小")})
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SysConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}
