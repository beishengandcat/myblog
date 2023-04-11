package com.lz.service.impl;

import com.lz.domain.LoginUser;
import com.lz.domain.ResponseResult;
import com.lz.domain.User;
import com.lz.domain.vo.BlogUserLoginVo;
import com.lz.domain.vo.UserInfoVo;
import com.lz.service.AdminLoginService;
import com.lz.utils.BeanCopyUtils;
import com.lz.utils.JwtUtil;
import com.lz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/26 22:06
 **/
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

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
        redisCache.setCacheObject("login:"+userId,loginUser);

        //把token返回
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);


        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout(Long id) {
        //删除redis中对应的值
        redisCache.deleteObject("login:" + id);
        return ResponseResult.okResult();
    }
}
