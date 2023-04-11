package com.lz.service.impl;

import com.google.gson.Gson;
import com.lz.domain.ResponseResult;
import com.lz.enums.AppHttpCodeEnum;
import com.lz.exception.SystemException;
import com.lz.service.UploadService;
import com.lz.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/21 22:16
 **/
@Service
public class UploadServiceImpl implements UploadService {

//    @Value("${oss.accessKey}")
    private String accessKey = "9UYZCDV5-4PMXQWQN8NoaeNaM2gqFkseNKxEhSGx";

//    @Value("${oss.secretKey}")
    private String secretKey = "k05JLYrHMTghJANr256P9Q30hBpPNb_lDaRAoLlw";

    private String bucket = "derrick-blog";


    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //判断文件类型
        //获取原始文件名
        String originalFilename = img.getOriginalFilename();
        //对原始文件名进行判断
        if(!originalFilename.endsWith(".png")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        //如果判断通过上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(img,filePath);//  2099/2/3/wqeqeqe.png
        return ResponseResult.okResult(url);
    }

    private String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rn8us2tnq.bkt.clouddn.com/" + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "www";
    }

}
