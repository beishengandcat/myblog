package com.lz.domain.vo;

import com.lz.domain.Role;
import com.lz.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2023/1/11 17:38
 **/
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAndRoleIdsVo {
    private User user;
    private List<Role> roles;
    private List<Long> roleIds;



}
