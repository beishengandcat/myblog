package com.lz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 功能描述：后台管理封装个人信息
 *
 * @Author: 李振
 * @Date: 2022/12/27 20:39
 **/

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVo {

    private List<String> permissions;

    private List<String> roles;

    private UserInfoVo user;

}
