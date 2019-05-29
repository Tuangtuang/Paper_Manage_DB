package com.example.demo.service;

import com.example.demo.dao.QuestionMapper;
import com.example.demo.dao.SubjectMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.entity.*;
import com.example.demo.model.overview.Result;
import com.example.demo.model.paperManager.AutoPaperRequest;
import com.example.demo.model.paperManager.AutoPaperResponse;
import com.example.demo.model.paperManager.Questions;
import com.example.demo.tool.ResultTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Iterator;
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
    
    

    /** 
    * @Description: 根据id删除题目 
    * @Param: [questionId, userId] 
    * @return: com.example.demo.model.overview.Result 
    * @Author: tyq 
    * @Date: 2019-05-29 
    */ 
    public Result deleteQuestion(String questionId,String userId){
//        检查身份是否合法
        User user=userMapper.selectByPrimaryKey(Integer.parseInt(userId));
        if(user==null){
            return ResultTool.error("该用户不存在");
        }
        if(user.getIdentity()==1){
            return ResultTool.error("操作者不合法");
        }
//        检查题目是否存在
        Question question=questionMapper.selectByPrimaryKey(Integer.parseInt(questionId));
        if(question==null){
            return ResultTool.error("要删除的题目不存在");
        }
        questionMapper.deleteByPrimaryKey(Integer.parseInt(questionId));
        return ResultTool.success();
    }



    public Result autoPaper(AutoPaperRequest autoPaperRequest){
        List<Integer> knowledge=CheckAndTransfer(autoPaperRequest.getSecondKnowledge());
        if(knowledge.isEmpty()==true){
            return ResultTool.error("二级知识点列表为空");
        }
        List<Integer> type=new LinkedList<Integer>();
        type.add(1);
        type.add(2);
        type.add(3);
        QuestionExample questionExample1=new QuestionExample();
        questionExample1.createCriteria().andSecondKnowledgeIdIn(knowledge).andTypeIdEqualTo(1);//填空题
        List<QuestionWithBLOBs> questionList1=questionMapper.selectByExampleWithBLOBs(questionExample1);

        QuestionExample questionExample2=new QuestionExample();
        questionExample2.createCriteria().andSecondKnowledgeIdIn(knowledge).andTypeIdEqualTo(2);//选择题
        List<QuestionWithBLOBs> questionList2=questionMapper.selectByExampleWithBLOBs(questionExample2);

        QuestionExample questionExample3=new QuestionExample();
        questionExample3.createCriteria().andSecondKnowledgeIdIn(knowledge).andTypeIdEqualTo(3);//简答题
        List<QuestionWithBLOBs> questionList3=questionMapper.selectByExampleWithBLOBs(questionExample3);

        List<String> choose=randomChoice(questionList1, getMin(Integer.parseInt(autoPaperRequest.getChoose()),questionList1.size()));
        List<String> blank = randomChoice(questionList2, getMin(Integer.parseInt(autoPaperRequest.getBlank()),questionList2.size()));
        List<String> answer=randomChoice(questionList3,getMin(Integer.parseInt(autoPaperRequest.getAnswer()),questionList3.size()));
        AutoPaperResponse autoPaperResponse=new AutoPaperResponse();
        autoPaperResponse.setAnswer(answer);
        autoPaperResponse.setBlank(blank);
        autoPaperResponse.setChoose(choose);
        return ResultTool.success(autoPaperResponse);
    }

    private int getMin(int x,int y){
        if(x<=y){
            return x;
        }
        else {
            return y;
        }
    }

    private List<Integer> CheckAndTransfer(List<String> id){
        List<Integer> target=new LinkedList<>();
        for(int i=0;i<id.size();i++){
            int tmp=Integer.parseInt(id.get(i));
            target.add(tmp);
        }
        return target;
    }

    public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, set);// 递归
        }
    }


    private List<String> randomChoice(List<QuestionWithBLOBs> resource,int n){
        HashSet<Integer> set=new HashSet<>();
        randomSet(0,resource.size()-1,n,set);
        List<String> target=new LinkedList<>();
//        target.add()
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            Integer tmp = it.next();
            target.add(resource.get(tmp).getContent());
        }
        return target;
    }
}
