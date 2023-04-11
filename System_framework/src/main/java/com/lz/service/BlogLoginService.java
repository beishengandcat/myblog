package com.lz.service;

import com.lz.domain.ResponseResult;
import com.lz.domain.User;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:33
 **/
public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
