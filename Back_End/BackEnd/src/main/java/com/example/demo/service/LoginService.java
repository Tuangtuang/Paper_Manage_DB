package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.login.TokenResponse;
import com.example.demo.login.UserLogin;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserExample;
import com.example.demo.model.overview.Result;
import com.example.demo.tool.JwtUtil;
import com.example.demo.tool.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 登陆
 * @author: tyq
 * @create: 2019-05-22 15:00
 **/
@Service
public class LoginService {

    @Resource
    private UserMapper userMapper;

    public Result StudentLogin(UserLogin userLogin){
        UserExample userExample=new UserExample();
        TokenResponse tokenResponse=new TokenResponse();
        userExample.createCriteria().andPhoneEqualTo(userLogin.getUserId()).andPasswordEqualTo(userLogin.getUserPassword());

        List<User> studentList=userMapper.selectByExample(userExample);
        if(studentList.isEmpty()==true){
            return ResultTool.error("用户名或密码错误");
        }
        tokenResponse.setIdentity(studentList.get(0).getIdentity().toString());
        tokenResponse.setToken(JwtUtil.createJwt(studentList.get(0).getId().toString()));
        tokenResponse.setUserName(studentList.get(0).getOthers());
        tokenResponse.setUserId(studentList.get(0).getId().toString());
        return ResultTool.success(tokenResponse);
    }
}
