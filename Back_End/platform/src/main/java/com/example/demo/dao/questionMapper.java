package com.example.demo.dao;

import com.example.demo.model.entity.question;
import com.example.demo.model.entity.questionExample;
import com.example.demo.model.entity.questionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface questionMapper {
    int countByExample(questionExample example);

    int deleteByExample(questionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(questionWithBLOBs record);

    int insertSelective(questionWithBLOBs record);

    List<questionWithBLOBs> selectByExampleWithBLOBs(questionExample example);

    List<question> selectByExample(questionExample example);

    questionWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") questionWithBLOBs record, @Param("example") questionExample example);

    int updateByExampleWithBLOBs(@Param("record") questionWithBLOBs record, @Param("example") questionExample example);

    int updateByExample(@Param("record") question record, @Param("example") questionExample example);

    int updateByPrimaryKeySelective(questionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(questionWithBLOBs record);

    int updateByPrimaryKey(question record);
}