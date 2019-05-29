package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.model.entity.*;
import com.example.demo.model.overview.Result;
import com.example.demo.tool.ResultTool;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 试题录入员
 * @author: tyq
 * @create: 2019-05-22 20:36
 **/
@Service
public class PaperInputService {

    @Resource
    private SchoolMapper schoolMapper;
    
    @Resource
    private SubjectMapper subjectMapper;

    @Resource
    private TypeMapper typeMapper;
    
    @Resource
    private FirstKnowledgeMapper firstKnowledgeMapper;
    
    @Resource
    private SecondKnowledgeMapper secondKnowledgeMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private  KeyMapper keyMapper;

    
    /** 
    * @Description: 获取所有学校信息 
    * @Param: [] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    public Result getAllSchool(){
        SchoolExample schoolExample=new SchoolExample();
        schoolExample.createCriteria().andIdIsNotNull();
        schoolExample.setOrderByClause("degree");
        List<School> schoolList=schoolMapper.selectByExample(schoolExample);
        if(schoolList.isEmpty()==true){
            return ResultTool.error("不存在学校");
        }
        return ResultTool.success(schoolList);
    }
    
    /** 
    * @Description: 获取所有的科目 
    * @Param: [] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    public Result getAllSubject(){
        SubjectExample subjectExample=new SubjectExample();
        subjectExample.createCriteria().andIdIsNotNull();
        subjectExample.setOrderByClause("content");
        List<Subject> subjectList=subjectMapper.selectByExample(subjectExample);
        if(subjectList.isEmpty()==true){
            return ResultTool.error("不存在科目");
        }
        return ResultTool.success(subjectList);
    }

    /**
    * @Description: 试题录入员获得所有题型接口
    * @Param: []
    * @return: com.example.demo.model.overview.Result
    * @Author: tyq
    * @Date: 2019-05-22
    */
    public Result getAllType(){
        TypeExample typeExample=new TypeExample();
        typeExample.createCriteria().andIdIsNotNull();
        typeExample.setOrderByClause("content");
        List<Type> typeList=typeMapper.selectByExample(typeExample);
        if(typeList.isEmpty()==true){
            return ResultTool.error("不存在题型");
        }
        return ResultTool.success(typeList);
    }


    /** 
    * @Description: 获取一级知识点内容 
    * @Param: [] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    public Result getFirstKnowledge(String subject){
        int subject_id=Integer.parseInt(subject);
//        检查科目是否存在
        Subject sub=subjectMapper.selectByPrimaryKey(subject_id);
        if (sub==null){
            return ResultTool.error("不存在该科目");
        }
        FirstKnowledgeExample  firstKnowledgeExample=new FirstKnowledgeExample();
        firstKnowledgeExample.createCriteria().andOthersEqualTo(subject);
        firstKnowledgeExample.setOrderByClause("content");
        List<FirstKnowledge> firstKnowledgeList=firstKnowledgeMapper.selectByExample(firstKnowledgeExample);
        if(firstKnowledgeList.isEmpty()==true){
            return ResultTool.error("不存在一级知识点");
        }
        return ResultTool.success(firstKnowledgeList);
    }
    
    /** 
    * @Description: 根据一级知识点获取二级知识点 
    * @Param: [first_id] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-22 
    */ 
    public Result getSecondByFirst(String first_id){
//        检查一级知识点是否存在
        int f_id=Integer.parseInt(first_id); 
        FirstKnowledge check=firstKnowledgeMapper.selectByPrimaryKey(f_id);
        if(check==null){
            return ResultTool.error("该一级知识点不存在");
        }
        SecondKnowledgeExample secondKnowledgeExample=new SecondKnowledgeExample();
        secondKnowledgeExample.createCriteria().andFirstIdEqualTo(f_id);
        secondKnowledgeExample.setOrderByClause("content");
        List<SecondKnowledge> secondKnowledgeList=secondKnowledgeMapper.selectByExample(secondKnowledgeExample);
        if(secondKnowledgeList.isEmpty()==true){
            return ResultTool.error("不存在二级知识点");
        }
        return ResultTool.success(secondKnowledgeList);
    }

//    public Result inputProblem(String userId,String subjectId,String schoolId,String typeId)

    public Result inputAnswer(String problemId,String content,String html)
    {
//        检查是否存在该问题
        QuestionWithBLOBs problem=questionMapper.selectByPrimaryKey(Integer.parseInt(problemId));
        if(problem==null){
            return ResultTool.error("不存在该问题");
        }
//        检查该题目是否存在答案
        KeyWithBLOBs key=new KeyWithBLOBs();
        key.setQuestionId(Integer.parseInt(problemId));
        key.setKeyContent(content);
        key.setPicture(html);
        keyMapper.insert(key);
        return ResultTool.success();

    }


}
