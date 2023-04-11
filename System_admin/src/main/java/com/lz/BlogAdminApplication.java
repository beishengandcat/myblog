package com.lz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述：博客后台启动类
 *
 * @Author: 李振
 * @Date: 2022/12/26 21:36
 **/
@SpringBootApplication
@MapperScan("com.lz.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
