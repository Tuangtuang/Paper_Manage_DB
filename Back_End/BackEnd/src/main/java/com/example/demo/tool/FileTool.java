package com.example.demo.tool;

import com.example.demo.model.overview.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: demo
 * @description: 文件上传下载接口
 * @author: tyq
 * @create: 2019-05-19 14:23
 **/
@Slf4j

public class FileTool{

    public static Result uploadFile(String filePath, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultTool.error("文件为空");
            }
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            log.info("上传的文件名为：" + fileName);
//            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            log.info("文件的后缀名为：" + suffixName);
////            获取文件后缀名前面的内容
//            String beforeSuffixName=fileName.substring(0,fileName.lastIndexOf(".")-1);
//            log.info("文件的前缀名为："+beforeSuffixName);

//            // 设置文件存储路径
//            String path = filePath+"/"+newFileName;

            java.io.File dest = new java.io.File(filePath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return ResultTool.success();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTool.error("上传失败");
    }
}
