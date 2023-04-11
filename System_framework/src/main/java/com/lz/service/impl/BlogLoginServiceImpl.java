package com.lz.service.impl;

import com.lz.domain.LoginUser;
import com.lz.domain.ResponseResult;
import com.lz.domain.User;
import com.lz.domain.vo.BlogUserLoginVo;
import com.lz.domain.vo.UserInfoVo;
import com.lz.service.BlogLoginService;
import com.lz.utils.BeanCopyUtils;
import com.lz.utils.JwtUtil;
import com.lz.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.Objects;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:34
 **/
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //获取userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();

        //通过jwt生成token
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //把token和userInfo封装返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);

        return ResponseResult.okResult(vo);
    }


    //退出登录
    @Override
    public ResponseResult logout() {

        //获取token 解析获取userid
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //获取UserId
        String userId = loginUser.getUser().getId().toString();
        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
