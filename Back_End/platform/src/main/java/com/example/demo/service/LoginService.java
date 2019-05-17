package com.example.demo.service;

import com.aliyuncs.exceptions.ClientException;
import com.example.demo.model.login.CheckCodeBack;
import com.example.demo.model.login.UserLogin;
import com.example.demo.model.overview.Result;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface LoginService {
    //    忘记密码发送验证码接口52
    Result SmsVerification(String phone, String identity) throws ClientException, NoSuchAlgorithmException, UnsupportedEncodingException;

    //    接受验证码接口53
    Result validateNum(CheckCodeBack checkCodeBack) throws com.aliyuncs.exceptions.ClientException, NoSuchAlgorithmException, UnsupportedEncodingException;

    //    用户登陆接口1
    Result UserLogin(UserLogin userLogin);

    //    邮件验证接口53
    Result sendMail(String mail, String identity) throws UnsupportedEncodingException, NoSuchAlgorithmException;


}
