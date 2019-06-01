package com.example.demo.service;

import com.example.demo.dao.FirstKnowledgeMapper;
import com.example.demo.dao.SchoolMapper;
import com.example.demo.dao.SubjectMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.entity.*;
import com.example.demo.model.overview.Result;
import com.example.demo.model.superManager.*;
import com.example.demo.tool.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
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

    @Resource
    SubjectMapper subjectMapper;

    @Resource
    FirstKnowledgeMapper firstKnowledgeMapper;

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


    /** 
    * @Description: 根据id删除用户 #22 
    * @Param: [deleteUser] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-29 
    */ 
    public Result deleteUser(DeleteUser deleteUser){
//        检查是否是管理员
        if(deleteUser.getUserId().equals(deleteUser.getDeleteUserId())){
            return ResultTool.error("不可以自己删除自己");
        }
        UserExample checkManage=new UserExample();
        checkManage.createCriteria().andIdEqualTo(Integer.parseInt(deleteUser.getUserId())).andIdentityEqualTo(3);
        List<User> managerList=userMapper.selectByExample(checkManage);
        if(managerList.isEmpty()==true){
            return ResultTool.error("管理员不存在");
        }
//        检查要删除的用户
        User user=userMapper.selectByPrimaryKey(Integer.parseInt(deleteUser.getDeleteUserId()));
        if(user==null){
            return ResultTool.error("要删除的用户不存在");
        }
        userMapper.deleteByPrimaryKey(user.getId());
        return ResultTool.success();
    }


    /** 
    * @Description: 获得所有用户 #21
    * @Param: [] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-29 
    */ 
    public Result getAllUser(String userId){
//        检查是否是管理员
        User manage=userMapper.selectByPrimaryKey(Integer.parseInt(userId));
        if(manage==null){
            return ResultTool.error("管理员不存在");
        }
        if(manage.getIdentity()!=3){
            return ResultTool.error("不是管理员没有权限查看");
        }

        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdIsNotNull();
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList.isEmpty()==true){
            return ResultTool.error("用户不存在");
        }
        List<AllUserInfo> allUserInfoList=new LinkedList<>();
        for(User item:userList){
            AllUserInfo allUserInfo=new AllUserInfo();
            allUserInfo.setId(item.getId().toString());
            allUserInfo.setIdentity(item.getIdentity().toString());
            allUserInfo.setPhone(item.getPhone());
            allUserInfo.setUserName(item.getOthers());
            allUserInfoList.add(allUserInfo);
        }
        return ResultTool.success(allUserInfoList);

    }


    /**
    * @Description: 添加一级知识点
    * @Param: [addFirstKnowledge]
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-05-29
    */
    public Result addFirstKnowledg(AddFirstKnowledge addFirstKnowledge){
//        检查是否是管理
        User manage=userMapper.selectByPrimaryKey(Integer.parseInt(addFirstKnowledge.getUserId()));
        if(manage==null){
            return ResultTool.error("管理员不存在");
        }
        if(manage.getIdentity()!=3){
            return ResultTool.error("不是管理员没有权限添加");
        }
//        检查是否存在id对应的科目
        Subject subject=subjectMapper.selectByPrimaryKey(Integer.parseInt(addFirstKnowledge.getSubjectId()));
        if(subject==null){
            return ResultTool.error("该科目不存在");
        }
//        添加一级知识点
        FirstKnowledge firstKnowledge=new FirstKnowledge();
        firstKnowledge.setOthers(addFirstKnowledge.getSubjectId());
        firstKnowledge.setContent(addFirstKnowledge.getFirstKnowledge());
        firstKnowledgeMapper.insert(firstKnowledge);
        FirstKnowledgeIdResponse firstKnowledgeIdResponse=new FirstKnowledgeIdResponse();
        firstKnowledgeIdResponse.setFisrtKnowledgeId(firstKnowledge.getId().toString());
        return ResultTool.success(firstKnowledgeIdResponse);

    }

    /**
    * @Description: 设置为超级管理员
    * @Param: [setIdentity]
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-05-29
    */
    public Result setPaperManager(SetIdentity setIdentity){
        //        检查是否是管理
        User manage=userMapper.selectByPrimaryKey(Integer.parseInt(setIdentity.getUserId()));
        if(manage==null){
            return ResultTool.error("管理员不存在");
        }
        if(manage.getIdentity()!=3){
            return ResultTool.error("不是管理员没有权限添加");
        }
//        检查被设置用户是否存在
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(setIdentity.getSetUserId()));
        if(user==null){
            return ResultTool.error("该用户不存在");
        }
        if(user.getIdentity()==2){
            return ResultTool.error("该用户已经是试题管理员");
        }
        user.setIdentity(2);
        userMapper.updateByPrimaryKeySelective(user);
        return ResultTool.success();
    }

}
