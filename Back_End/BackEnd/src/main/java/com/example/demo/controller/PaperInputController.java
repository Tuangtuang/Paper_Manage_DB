package com.example.demo.controller;

import com.example.demo.model.overview.Result;
import com.example.demo.service.PaperInputService;
import com.example.demo.service.SuperManageService;
import com.example.demo.tool.JwtUtil;
import com.example.demo.tool.ResultTool;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: demo
 * @description: 试题录入员
 * @author: tyq
 * @create: 2019-05-22 20:53
 **/
@CrossOrigin
@RestController
public class PaperInputController {

    @Resource
    private PaperInputService paperInputService;

    /** 
    * @Description: 获取所有学校 
    * @Param: [userId, httpServletRequest] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22
    */ 
    @GetMapping("/paperInput/getSchool")
    public Result getAllSchools(@RequestParam("userId")String userId, HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!userId.equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return paperInputService.getAllSchool();
    }

    /** 
    * @Description: 获取所有学科 
    * @Param: [userId, httpServletRequest] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    @GetMapping("/paperInput/getSubject")
    public Result getAllSubject(@Param("userId")String userId,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!userId.equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return paperInputService.getAllSubject();
    }


    /**
    * @Description: 获得所有题型
    * @Param: [userId, httpServletRequest]
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-05-22
    */
    @GetMapping("/PaperInput/getType")
    public Result getAllType(@RequestParam("userId")String userId,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!userId.equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return paperInputService.getAllType();
    }


    /** 
    * @Description: 获取一级知识点
    * @Param: [userId, httpServletRequest] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    @GetMapping("/PaperInput/getFirstKnowledge")
    public Result getFirstKnowledge(@RequestParam("userId") String userId,@RequestParam("subjectId")String subject, HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!userId.equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return paperInputService.getFirstKnowledge(subject);
    }

    /** 
    * @Description: 试题录入员根据一级知识点获得二级知识点 #7
    * @Param: [userId, first_id, httpServletRequest] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    @GetMapping("/PaperInput/getSecondKnowledge")
    public Result getSecondKnowledgeByFirst(@RequestParam("userId")String userId,@RequestParam("firstKnowledgeId") String first_id,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!userId.equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return paperInputService.getSecondByFirst(first_id);
    }


}
