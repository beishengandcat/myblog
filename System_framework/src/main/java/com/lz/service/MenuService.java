package com.lz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.Menu;
import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-12-27 20:32:56
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);


    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    //查询所有的菜单
    List<Menu> selectMenuList(Menu menu);

    //是否有子菜单
    boolean hasChild(Long menuId);

    List<Long> selectMenuListByRoleId(Long roleId);
}

