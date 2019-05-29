package com.example.demo.controller;

import com.example.demo.model.overview.Result;
import com.example.demo.model.superManager.*;
import com.example.demo.service.SuperManageService;
import com.example.demo.tool.JwtUtil;
import com.example.demo.tool.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: demo
 * @description: 超级用户管理
 * @author: tyq
 * @create: 2019-05-22 19:18
 **/
@CrossOrigin
@RestController
@Slf4j
public class SuperManageController {

    @Resource
    private SuperManageService superManageService;

    @PostMapping("/super/addUser")
    public Result addUser(@RequestBody UserInfo userInfo, HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!userInfo.getUserId().equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return superManageService.addUser(userInfo);
    }

    @PostMapping("/SuperManager/addSchool")
    public Result addSchool(@RequestBody SchoolInfo schoolInfo,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!schoolInfo.getUserId().equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        log.info(schoolInfo.getSchool()+schoolInfo.getDegree()+schoolInfo.getUserId());
        return superManageService.addSchool(schoolInfo);
    }

    @PostMapping("/userManage/deleteUser")
    public Result deleteUser(@RequestBody DeleteUser deleteUser,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!deleteUser.getUserId().equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return superManageService.deleteUser(deleteUser);
    }

    @GetMapping("/userManage/getUserList")
    public Result getAll(@RequestParam("userId")String userId,HttpServletRequest httpServletRequest){
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
        return superManageService.getAllUser(userId);
    }

    @PostMapping("/SuperManager/addFirstKown")
    public Result addFirst(@RequestBody AddFirstKnowledge addFirstKnowledge,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!addFirstKnowledge.getUserId().equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return superManageService.addFirstKnowledg(addFirstKnowledge);
    }

    @PostMapping("/userManage/setUserType")
    public Result setIdentity(@RequestBody SetIdentity setIdentity,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!setIdentity.getUserId().equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return superManageService.setPaperManager(setIdentity);
    }
}
