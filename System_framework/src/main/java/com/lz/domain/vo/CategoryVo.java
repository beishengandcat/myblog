package com.lz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：分类
 *
 * @Author: 李振
 * @Date: 2022/12/14 21:41
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    private Long id;
    private String name;
    //描述
    private String description;
}
