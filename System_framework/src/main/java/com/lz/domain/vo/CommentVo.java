package com.lz.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/19 20:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    @TableId
    private Long id;

    //文章id
    private Long articleId;

    //根评论id
    private Long rootId;

    //评论内容
    private String content;

    //所回复的目标评论的userid
    private Long toCommentUserId;

    private String toCommentUserName;

    //回复目标评论id
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String username;

    //子评论
    private List<CommentVo> children;
}
