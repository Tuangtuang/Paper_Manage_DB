package com.example.demo.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.demo.login.CheckCode;
import com.example.demo.login.CheckCodeBack;
import com.example.demo.login.UserLogin;
import com.example.demo.model.overview.Result;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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

    @GetMapping("/GetValidateCode")
    public Result getValidateCode(@RequestParam("phone") String phone)throws ClientException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return loginService.SmsVerification(phone);
    }

    @PostMapping("/ValidateCheck")
    public Result checkValidateCode(@RequestBody CheckCodeBack checkCodeback)throws com.aliyuncs.exceptions.ClientException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return loginService.validateNum(checkCodeback);
    }
}
