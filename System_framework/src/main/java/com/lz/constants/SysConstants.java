package com.lz.constants;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/14 20:49
 **/
public class SysConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 分类状态是正常状态
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 评论类型为根评论
     */
    public static final Long COMMENT_ROOT = -1L;

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";

    /**
     * 评论类型为：友链评论
     */
    public static final String LINK_COMMENT = "1";

    public static final String MENU = "C";

    public static final String BUTTON = "F";

    /** 正常状态 */
    public static final String NORMAL = "0";

    //判断是否是后台用户
    public static final String ADMIN = "1";
}
