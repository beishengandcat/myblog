package com.lz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.Category;
import com.lz.domain.ResponseResult;
import com.lz.domain.vo.CategoryVo;
import com.lz.domain.vo.PageVo;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-12-14 21:16:21
 */
public interface CategoryService extends IService<Category> {

    //查询所有的分类信息
    ResponseResult getCategoryList();

    //后台查询所有的分类信息
    List<CategoryVo> listAllCategory();


    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}

