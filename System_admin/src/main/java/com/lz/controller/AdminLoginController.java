package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.domain.LoginUser;
import com.lz.domain.Menu;
import com.lz.domain.ResponseResult;
import com.lz.domain.User;
import com.lz.domain.vo.AdminUserInfoVo;
import com.lz.domain.vo.RoutersVo;
import com.lz.domain.vo.UserInfoVo;
import com.lz.enums.AppHttpCodeEnum;
import com.lz.exception.SystemException;
import com.lz.service.AdminLoginService;
import com.lz.service.MenuService;
import com.lz.service.RoleService;
import com.lz.utils.BeanCopyUtils;
import com.lz.utils.RedisCache;
import com.lz.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 功能描述：后台管理系统登录
 *
 * @Author: 李振
 * @Date: 2022/12/26 21:58
 **/

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/login")
    @SystemLog(businessName = "登录")
    @ApiOperation(value = "登录")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }



    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }


    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    @SystemLog(businessName = "注销用户")
    @ApiOperation(value = "注销")
    public ResponseResult logout(){
        //获取当前用户的id
        Long userId = SecurityUtils.getUserId();
        return loginService.logout(userId);
    }



}
