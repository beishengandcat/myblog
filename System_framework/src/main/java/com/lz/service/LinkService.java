package com.lz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.Link;
import com.lz.domain.ResponseResult;
import com.lz.domain.vo.PageVo;

/**
 * 友链(SgLink)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 10:57:04
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}

