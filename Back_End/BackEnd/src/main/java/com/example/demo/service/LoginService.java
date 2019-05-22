package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.login.CheckCode;
import com.example.demo.login.CheckCodeBack;
import com.example.demo.login.TokenResponse;
import com.example.demo.login.UserLogin;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserExample;
import com.example.demo.model.overview.Result;
import com.example.demo.tool.JwtUtil;
import com.example.demo.tool.MD5Utils;
import com.example.demo.tool.ResultTool;
import com.example.demo.tool.TextCheck;
import lombok.extern.slf4j.Slf4j;
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
 * @create: 2019-05-22 15:00
 **/
@Service
@Slf4j
public class LoginService {

    @Resource
    private UserMapper userMapper;

    private static final String KEY = "wulaoshizuikoumen";


    /**
    * @Description: 用户登陆接口
    * @Param: [userLogin]
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-05-22
    */
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



    /**
    * @Description: 短信校验
    * @Param: [phone]
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-05-22
    */
    public Result SmsVerification(String phone) throws com.aliyuncs.exceptions.ClientException, NoSuchAlgorithmException, UnsupportedEncodingException {
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
    * @Description: 检查用户是否存在 
    * @Param: [phone] 
    * @return: boolean 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    private boolean checkPhone(String phone){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList.isEmpty()==true){
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
    * @Date: 2019-05-22 
    */ 
    public Result validateNum(CheckCodeBack checkCodeBack)  throws com.aliyuncs.exceptions.ClientException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String requestHash = checkCodeBack.getCode();//hash串
        String tamp = checkCodeBack.getTime();//来自前端的时间
        String msgNum = checkCodeBack.getInputCode();//来自前端的用户输入的验证码
        String userId=checkCodeBack.getUserId();
        String hash = MD5Utils.encodeByMd5((KEY +  "#" + msgNum+"#"+userId));//生成新的MD5值
        log.info(msgNum+" "+hash);
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


}
