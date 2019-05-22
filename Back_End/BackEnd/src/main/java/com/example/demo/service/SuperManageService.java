package com.example.demo.service;

import com.example.demo.dao.SchoolMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.SchoolExample;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.UserExample;
import com.example.demo.model.overview.Result;
import com.example.demo.model.superManager.SchoolId;
import com.example.demo.model.superManager.SchoolInfo;
import com.example.demo.model.superManager.UserId;
import com.example.demo.model.superManager.UserInfo;
import com.example.demo.tool.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 超级用户添加用户
 * @author: tyq
 * @create: 2019-05-22 19:13
 **/
@Service
@Slf4j
public class SuperManageService {
    @Resource
    UserMapper userMapper;

    @Resource
    SchoolMapper schoolMapper;


    /** 
    * @Description: 添加用户 
    * @Param: [userInfo] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    public Result addUser(UserInfo userInfo){
//        检查是否是超级管理员操作的
        UserExample superCheck=new UserExample();
        superCheck.createCriteria().andIdEqualTo(Integer.parseInt(userInfo.getUserId())).andIdentityEqualTo(3);
        List<User> manager=userMapper.selectByExample(superCheck);
        if(manager.isEmpty()==true){
            return ResultTool.error("您不是超级用户");
        }
//        检查该用户是否存在
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(userInfo.getUserPhone());
        List<User> checkList=userMapper.selectByExample(userExample);
        if(checkList.isEmpty()==false){
            return ResultTool.error("该用户已存在！");
        }
        User record=new User();
        record.setIdentity(Integer.parseInt(userInfo.getIdentity()));
        record.setOthers(userInfo.getUserName());
        record.setPassword(userInfo.getPassword());
        record.setPhone(userInfo.getUserPhone());
        userMapper.insert(record);
        UserId userId=new UserId();
        userId.setId(record.getId().toString());
        return ResultTool.success(userId);
    }

    /** 
    * @Description: 超级管理员添加学校 
    * @Param: [schoolInfo] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    public Result addSchool(SchoolInfo schoolInfo){
//        检查是否是超级管理员操作
        UserExample superCheck=new UserExample();
        superCheck.createCriteria().andIdEqualTo(Integer.parseInt(schoolInfo.getUserId())).andIdentityEqualTo(3);
        List<User> manager=userMapper.selectByExample(superCheck);
        if(manager.isEmpty()==true){
            return ResultTool.error("您不是超级用户");
        }
//        检查学校是否存在
        SchoolExample schoolExample=new SchoolExample();
        schoolExample.createCriteria().andNameEqualTo(schoolInfo.getSchool());
        List<School> schoolList=schoolMapper.selectByExample(schoolExample);
        if(schoolList.isEmpty()==false){
            return ResultTool.error("该学校已经存在");
        }
        School school=new School();
        school.setName(schoolInfo.getSchool());
        school.setDegree(Integer.parseInt(schoolInfo.getDegree()));
        schoolMapper.insert(school);
        SchoolId schoolId=new SchoolId();
        schoolId.setSchoolId(school.getId().toString());
        return ResultTool.success(schoolId);

    }
}
