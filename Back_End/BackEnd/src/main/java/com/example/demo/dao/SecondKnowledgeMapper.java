package com.example.demo.dao;

import com.example.demo.model.entity.SecondKnowledge;
import com.example.demo.model.entity.SecondKnowledgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SecondKnowledgeMapper {
    int countByExample(SecondKnowledgeExample example);

    int deleteByExample(SecondKnowledgeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SecondKnowledge record);

    int insertSelective(SecondKnowledge record);

    List<SecondKnowledge> selectByExample(SecondKnowledgeExample example);

    SecondKnowledge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SecondKnowledge record, @Param("example") SecondKnowledgeExample example);

    int updateByExample(@Param("record") SecondKnowledge record, @Param("example") SecondKnowledgeExample example);

    int updateByPrimaryKeySelective(SecondKnowledge record);

    int updateByPrimaryKey(SecondKnowledge record);
}