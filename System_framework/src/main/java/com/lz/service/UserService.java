package com.lz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lz.domain.ResponseResult;
import com.lz.domain.User;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 11:22:35
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult selectUserPage(User user, Integer pageNum, Integer pageSize);

    boolean checkUserNameUnique(String userName);

    boolean checkPhoneUnique(User user);

    boolean checkEmailUnique(User user);

    ResponseResult addUser(User user);

    void updateUser(User user);

}

