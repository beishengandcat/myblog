package com.lz.controller;

import com.lz.domain.ResponseResult;
import com.lz.domain.Role;
import com.lz.domain.dto.ChangeRoleStatusDto;
import com.lz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述：角色管理
 *
 * @Author: 李振
 * @Date: 2023/1/11 16:59
 **/
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有状态正常的角色
    @GetMapping("/listAllRole")
    public ResponseResult listAllRole(){
        List<Role> roles = roleService.selectRoleAll();
        return ResponseResult.okResult(roles);
    }

    @GetMapping("/list")
    public ResponseResult list(Role role, Integer pageNum, Integer pageSize) {
        return roleService.selectRolePage(role,pageNum,pageSize);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    public ResponseResult getInfo(@PathVariable Long roleId)
    {
        Role role = roleService.getById(roleId);
        return ResponseResult.okResult(role);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto roleStatusDto){
        Role role = new Role();
        role.setId(roleStatusDto.getRoleId());
        role.setStatus(roleStatusDto.getStatus());
        return ResponseResult.okResult(roleService.updateById(role));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public ResponseResult add( @RequestBody Role role)
    {
        roleService.insertRole(role);
        return ResponseResult.okResult();

    }


    /**
     * 修改保存角色
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Role role)
    {
        roleService.updateRole(role);
        return ResponseResult.okResult();
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }

}
