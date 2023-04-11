package com.lz.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述：友链封装类
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {

    @TableId
    private Long id;

    private String name;

    private String logo;

    private String description;

    //网站地址
    private String address;

}
