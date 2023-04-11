package com.lz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.constants.SysConstants;
import com.lz.domain.LoginUser;
import com.lz.domain.User;
import com.lz.mapper.MenuMapper;
import com.lz.mapper.UserMapper;
import com.lz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:39
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户 如果没查到，抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }

        //TODO 查询对应的权限信息封装
        //TODO 如果是后台用户才需要查询权限封装
        if (user.getType().equals(SysConstants.ADMIN)){
            List<String> list = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,list);

        }

        //返回用户信息
        return new LoginUser(user,null);
    }
}
