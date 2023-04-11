package com.lz.controller;

import com.lz.annotation.SystemLog;
import com.lz.domain.ResponseResult;
import com.lz.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述：上传头像
 *
 * @Author: 李振
 * @Date: 2022/12/21 22:13
 **/
@RestController
@Api(tags = "上传头像至七牛云",description = "上传头像接口")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping("/upload")
    @SystemLog(businessName = "上传头像")
    @ApiOperation(value = "七牛云OSS上传头像")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
