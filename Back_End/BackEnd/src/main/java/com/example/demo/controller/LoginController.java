package com.example.demo.controller;

import com.example.demo.login.UserLogin;
import com.example.demo.model.overview.Result;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: demo
 * @description: 登陆controller层
 * @author: tyq
 * @create: 2019-05-22 15:05
 **/
@CrossOrigin
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result Login(@RequestBody UserLogin userLogin){
        return loginService.StudentLogin(userLogin);
    }
}
