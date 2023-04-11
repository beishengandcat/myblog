package com.lz.service;

import com.lz.domain.ResponseResult;
import com.lz.domain.User;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/26 22:06
 **/
public interface AdminLoginService {
    ResponseResult login(User user);

    ResponseResult logout(Long id);
}
