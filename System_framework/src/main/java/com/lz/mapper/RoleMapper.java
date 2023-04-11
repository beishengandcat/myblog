package com.lz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.domain.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-27 20:36:32
 */
public interface RoleMapper extends BaseMapper<Role> {

    //通过用户id查询对应角色信息
    List<String> selectRoleKeyByUserId(Long id);

    List<Long> selectRoleIdByUserId(Long userId);
}
