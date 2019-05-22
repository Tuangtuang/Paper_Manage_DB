package com.example.demo.dao;

import com.example.demo.model.entity.FirstKnowledge;
import com.example.demo.model.entity.FirstKnowledgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FirstKnowledgeMapper {
    int countByExample(FirstKnowledgeExample example);

    int deleteByExample(FirstKnowledgeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FirstKnowledge record);

    int insertSelective(FirstKnowledge record);

    List<FirstKnowledge> selectByExample(FirstKnowledgeExample example);

    FirstKnowledge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FirstKnowledge record, @Param("example") FirstKnowledgeExample example);

    int updateByExample(@Param("record") FirstKnowledge record, @Param("example") FirstKnowledgeExample example);

    int updateByPrimaryKeySelective(FirstKnowledge record);

    int updateByPrimaryKey(FirstKnowledge record);
}