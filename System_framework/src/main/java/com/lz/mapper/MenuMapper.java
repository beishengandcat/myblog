package com.lz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.domain.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-27 20:32:56
 */
public interface MenuMapper extends BaseMapper<Menu> {

    //通过用户id查询该用户所具有的权限
    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Long> selectMenuListByRoleId(Long roleId);
}

