package com.example.demo.controller;

import com.example.demo.tool.QiniuUtil;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @program: demo
 * @description: 测试
 * @author: tyq
 * @create: 2019-05-28 21:21
 **/
@CrossOrigin
@RestController
public class PictureController {

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    private String postUserInforUpDate(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        // 用来获取其他参数
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        String name = params.getParameter("username");
        if (!multipartFile.isEmpty()) {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String path = QiniuUtil.uploadQNImg(inputStream, "image"); // KeyUtil.genUniqueKey()生成图片的随机名
            System.out.print("七牛云返回的图片链接:" + path);
            return path;
        }
        return "上传失败";
    }
}
