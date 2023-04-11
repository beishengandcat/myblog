package com.lz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/14 17:51
 **/
@SpringBootApplication
@MapperScan("com.lz.mapper")
@EnableScheduling
@EnableSwagger2
@EnableTransactionManagement
public class LzBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LzBlogApplication.class, args);
    }
}
