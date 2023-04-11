package com.lz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：标签列表
 *
 * @Author: 李振
 * @Date: 2022/12/29 21:05
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagListDto {

    private String name;
    private String remark;

}
