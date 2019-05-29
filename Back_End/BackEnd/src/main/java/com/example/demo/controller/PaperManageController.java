package com.example.demo.controller;

import com.example.demo.model.overview.Result;
import com.example.demo.model.paperManager.QuestionId;
import com.example.demo.service.PaperManageService;
import com.example.demo.tool.JwtUtil;
import com.example.demo.tool.ResultTool;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: demo
 * @description:
 * @author: tyq
 * @create: 2019-05-29 20:54
 **/
@CrossOrigin
@RestController
public class PaperManageController {

    @Resource
    private PaperManageService paperManageService;


    @GetMapping("/questionManage/getAllList")
    public Result getAllQuestion(@RequestParam("userId")String userId, HttpServletRequest httpServletRequest){
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
        return paperManageService.getAllProblems(userId);
    }


    @PostMapping("/userManage/deleteQuestion")
    public Result deleteQuestionById(@RequestBody QuestionId questionId,HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("Authorization");
        String id;
        try {
            id= JwtUtil.parseJwt(token);
        }catch (Exception e){
            return ResultTool.error("登陆状态无效，无法解析token");
        }
        if (!questionId.getUserId().equals(id)){
            return ResultTool.error("登陆状态无效，token和id不一致 ");
        }
        return paperManageService.deleteQuestion(questionId.getQuestionId(),questionId.getUserId());

    }
}
