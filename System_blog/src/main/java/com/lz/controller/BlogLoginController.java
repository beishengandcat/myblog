package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.domain.ResponseResult;
import com.lz.domain.User;
import com.lz.enums.AppHttpCodeEnum;
import com.lz.exception.SystemException;
import com.lz.service.BlogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：用户的登录和退出
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:31
 **/

@RestController
@Api(tags = "用户登录",description = "用户登录相关接口")
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    @ApiOperation(value = "登录")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            //提示必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
       return blogService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(businessName = "注销用户")
    @ApiOperation(value = "注销")
    public ResponseResult logout(){
        return blogService.logout();
    }
}
