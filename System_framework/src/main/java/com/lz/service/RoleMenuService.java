package com.lz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.RoleMenu;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2023/1/11 17:15
 **/
public interface RoleMenuService extends IService<RoleMenu> {
    void deleteRoleMenuByRoleId(Long id);
}
