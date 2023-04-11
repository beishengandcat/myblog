package com.lz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：返回给前端的用户信息封装
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:54
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUserLoginVo {
    private String token;
    private UserInfoVo userInfo;
}
