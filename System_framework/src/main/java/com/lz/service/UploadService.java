package com.lz.service;

import com.lz.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/21 22:15
 **/
public interface UploadService {
   ResponseResult uploadImg(MultipartFile file);
}
