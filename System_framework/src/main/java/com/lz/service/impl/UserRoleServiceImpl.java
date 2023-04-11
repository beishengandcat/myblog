package com.lz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.domain.UserRole;
import com.lz.mapper.UserRoleMapper;
import com.lz.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2023/1/11 17:34
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
