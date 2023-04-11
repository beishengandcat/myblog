package com.lz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2023/1/11 15:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    private Long id;
    //标签名
    private String name;
}
