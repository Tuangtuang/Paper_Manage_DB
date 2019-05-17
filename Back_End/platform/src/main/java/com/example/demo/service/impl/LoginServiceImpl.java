package com.example.demo.service.impl;


import com.example.demo.dao.userMapper;
import com.example.demo.model.entity.user;
import com.example.demo.model.entity.userExample;
import com.example.demo.model.login.CheckCode;
import com.example.demo.model.login.CheckCodeBack;
import com.example.demo.model.login.TokenResponse;
import com.example.demo.model.login.UserLogin;
import com.example.demo.model.overview.Result;
import com.example.demo.service.LoginService;
import com.example.demo.tool.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @program: demo
 * @description: 登陆
 * @author: tyq
 * @create: 2019-04-25 15:31
 **/
@Service

public class LoginServiceImpl implements LoginService {

    private static final String KEY = "wulaoshizuikoumen";

    @Resource
    private userMapper userMapper;



   
    /** 
    * @Description: 接口52，用户忘记密码，进行短信验证 
    * @Param: [phone] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-04-26 
    */ 
    @Override
    public Result SmsVerification(String phone, String identity) throws com.aliyuncs.exceptions.ClientException, NoSuchAlgorithmException, UnsupportedEncodingException {
        if(checkPhone(phone)==true){
            String phoneNumber = phone;
            String randomNum = getMsgCode();// 生成随机数
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 1);
            String currentTime = sf.format(c.getTime());// 生成1分钟后时间，用户校验是否过期
            TextCheck.sendSms(phone,randomNum); //此处执行发送短信验证码方法
            String hash =  MD5Utils.encodeByMd5((KEY + "#"  + randomNum+"#"+phone));//生成MD5值
            CheckCode checkCode=new CheckCode();
            checkCode.setCode(hash);
            checkCode.setTime(currentTime);
            return ResultTool.success(checkCode); //将hash值和tamp时间返回给前端
        }
        return ResultTool.error("该用户不存在！");
    }


    /**
    * @Description: 查看手机号是否存在
    * @Param: [phone]
    * @return: boolean
    * @Author: tyq
    * @Date: 2019-04-26
    */
    private boolean checkPhone(String phone){
        userExample userExample=new userExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<user> superManagerList=userMapper.selectByExample(userExample);
        if(superManagerList.isEmpty()==true){
            //  列表是空的，说明改手机号码不存在
            return false;
        }
        return true;
    }



    /** 
    * @Description: 随机生产六位验证码 
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: tyq 
    * @Date: 2019-04-26
    */ 
    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }


    /**
    * @Description: 确认验证码是否正确
    * @Param: [checkCodeBack]
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-04-26
    */
    @Override
    public Result validateNum(CheckCodeBack checkCodeBack)  throws com.aliyuncs.exceptions.ClientException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String requestHash = checkCodeBack.getCode();//hash串
        String tamp = checkCodeBack.getTime();//来自前端的时间
        String msgNum = checkCodeBack.getInputCode();//来自前端的用户输入的验证码
        String userId=checkCodeBack.getUserId();
        String hash = MD5Utils.encodeByMd5((KEY +  "#" + msgNum+"#"+userId));//生成新的MD5值
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String currentTime = sf.format(c.getTime());
        if (tamp.compareTo(currentTime) > 0) {
            if (hash.equalsIgnoreCase(requestHash)) {
                //校验成功
                return ResultTool.success();
            } else {
                //验证码不正确，校验失败
                return ResultTool.error("验证码不正确，校验失败");
            }
        } else {
            // 超时
//            System.out.println("当前时间"+currentTime+" 前端传过来的时间"+tamp);
            return ResultTool.error("超时");
        }
    }


    /**
     * @Description: 老师用户登陆接口
     * @Param: [userLogin]
     * @return: com.example.demo.model.overview.Result
     * @Author: tyq
     * @Date: 2019-04-26
     */
    public Result UserLogin(UserLogin userLogin){
        userExample userExample=new userExample();
        TokenResponse tokenResponse=new TokenResponse();
            // 手机号登陆
        userExample.createCriteria().andPhoneEqualTo(userLogin.getUserId()).andPasswordEqualTo(userLogin.getUserPassword());

        List<user> teacherList=userMapper.selectByExample(userExample);
        if(teacherList.isEmpty()==true){
            return ResultTool.error("用户名或密码错误");
        }
        tokenResponse.setToken(JwtUtil.createJwt(teacherList.get(0).getId().toString()));
        tokenResponse.setIdentity(teacherList.get(0).getIdentity().toString());
        tokenResponse.setUserId(teacherList.get(0).getId().toString());
        return ResultTool.success(tokenResponse);
    }




}
