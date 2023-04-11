package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.domain.ResponseResult;
import com.lz.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：友链信息
 *
 * @Author: 李振
 * @Date: 2022/12/15 11:01
 **/
@RestController
@RequestMapping("/link")
@Api(tags = "友链",description = "友链相关接口")
public class LinkController {

    @Autowired
    private LinkService linkService;

    //查询所有的友链信息
    @GetMapping("/getAllLink")
    @SystemLog(businessName = "查询所有的友链信息")
    @ApiOperation(value = "查询所有的友链信息")
    public ResponseResult getAllLink(){
       return linkService.getAllLink();
    }
}
