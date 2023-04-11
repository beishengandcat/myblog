package com.lz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.ResponseResult;
import com.lz.domain.Tag;
import com.lz.domain.dto.TagListDto;
import com.lz.domain.vo.PageVo;
import com.lz.domain.vo.TagVo;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-12-26 21:40:07
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult add(Tag tag);

    //查询所有的标签列表
    List<TagVo> listAllTag();
}

