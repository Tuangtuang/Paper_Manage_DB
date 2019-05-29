package com.example.demo.service;

import com.example.demo.dao.QuestionMapper;
import com.example.demo.dao.SubjectMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.entity.*;
import com.example.demo.model.overview.Result;
import com.example.demo.model.paperManager.Questions;
import com.example.demo.tool.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: demo
 * @description: 试题管理员
 * @author: tyq
 * @create: 2019-05-29 20:23
 **/
@Service
public class PaperManageService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private SubjectMapper subjectMapper;

//    获得所有题目 #24
    public Result getAllProblems(String userId){
//        用户身份检查
        User user=userMapper.selectByPrimaryKey(Integer.parseInt(userId));
        if(user==null){
            return ResultTool.error("用户不存在");
        }
        if(user.getIdentity()==1){
            return ResultTool.error("您没有权限");
        }
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andIdIsNotNull();
        List<QuestionWithBLOBs> questionWithBLOBsList=questionMapper.selectByExampleWithBLOBs(questionExample);
        if(questionWithBLOBsList.isEmpty()==true){
            return ResultTool.error("没有题目");
        }
        List<Questions> questionsList=new LinkedList<>();
        for(QuestionWithBLOBs item:questionWithBLOBsList){
            Questions info=new Questions();
            info.setContent(item.getContent());
            info.setSubject(getSubjectName(item.getSubjectId()));
            info.setId(item.getId().toString());
            questionsList.add(info);
        }
        return ResultTool.success(questionsList);

    }

    private String getSubjectName(int id){
        Subject subject;
        subject=subjectMapper.selectByPrimaryKey(id);
        if(subject==null){
            return null;
        }
        return subject.getContent();
    }
}
