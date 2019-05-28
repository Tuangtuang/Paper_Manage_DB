package com.example.demo.tool;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @program: demo
 * @description: 七牛云接口
 * @author: tyq
 * @create: 2019-05-28 21:11
 **/
@Slf4j
public class QiniuUtil {
//    private static final Logger logger = Logger.getLogger(QiniuUtil.class);



    private static final String ACCESS_KEY = "gW4PAPndrl_j0-gABLz3IkmIh8rIgNRfMuS7Q2Hs";
    private static final String SECRET_KEY = "3u-FyGkWpgxvgZTYAeI74JTlyLx3QtCMpbE8lHiz";

    // 要上传的空间名称
    private static final String BUCKETNAME = "picture";


    // 外链默认域名
    private static final String DOMAIN = "http://paper";

    /**
     // 密钥
     private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
     // 外链默认域名
     private static final String DOMAIN = "http://";
     /**
     * 将图片上传到七牛云
     */
    public static String uploadQNImg(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String upToken = auth.uploadToken(BUCKETNAME);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = DOMAIN + "/" + putRet.key;
                // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}



