package com.lz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述：修改用户角色封装类
 *
 * @Author: 李振
 * @Date: 2023/1/11 17:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoleStatusDto {

    private Long roleId;
    private String status;
}
