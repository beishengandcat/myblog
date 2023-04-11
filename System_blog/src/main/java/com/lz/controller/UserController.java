package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.domain.ResponseResult;
import com.lz.domain.User;
import com.lz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：用户信息操作
 *
 * @Author: 李振
 * @Date: 2022/12/21 21:29
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "个人信息",description = "个人信息相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(businessName = "查询用户个人信息")
    @ApiOperation(value = "查询用户个人信息")
    public ResponseResult userInfo(){
      return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户个人信息")
    @ApiOperation(value = "更新用户个人信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(businessName = "注册用户")
    @ApiOperation(value = "注册用户")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
