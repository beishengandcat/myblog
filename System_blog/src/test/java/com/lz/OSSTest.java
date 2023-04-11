package com.lz;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/21 21:55
 **/
@SpringBootTest
@ConfigurationProperties(prefix = "oss")
public class OSSTest {

        private String accessKey;
        private String secretKey;
        private String bucket;

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Test
        public void test(){
            //构造一个带指定 Region 对象的配置类
            Configuration cfg = new Configuration(Region.autoRegion());
            cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释

            UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
            String accessKey = "9UYZCDV5-4PMXQWQN8NoaeNaM2gqFkseNKxEhSGx";
            String secretKey = "k05JLYrHMTghJANr256P9Q30hBpPNb_lDaRAoLlw";
            String bucket = "derrick-blog";

//默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = "2022/derrick.png";

            try {
//                byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//                ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
                InputStream inputStream = new FileInputStream("D:\\桌面\\IMG_1410(20221201-172015).JPG");

                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);

                try {
                    Response response = uploadManager.put(inputStream,key,upToken,null, null);
                    //解析上传成功的结果
                    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                    System.out.println(putRet.key);
                    System.out.println(putRet.hash);
                } catch (QiniuException ex) {
                    Response r = ex.response;
                    System.err.println(r.toString());
                    try {
                        System.err.println(r.bodyString());
                    } catch (QiniuException ex2) {
                        //ignore
                    }
                }
            } catch (Exception ex) {
                //ignore
            }

        }
}