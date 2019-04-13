package com.example.demo.dao;

import com.example.demo.model.entity.first_knowledge;
import com.example.demo.model.entity.first_knowledgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface first_knowledgeMapper {
    int countByExample(first_knowledgeExample example);

    int deleteByExample(first_knowledgeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(first_knowledge record);

    int insertSelective(first_knowledge record);

    List<first_knowledge> selectByExample(first_knowledgeExample example);

    first_knowledge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") first_knowledge record, @Param("example") first_knowledgeExample example);

    int updateByExample(@Param("record") first_knowledge record, @Param("example") first_knowledgeExample example);

    int updateByPrimaryKeySelective(first_knowledge record);

    int updateByPrimaryKey(first_knowledge record);
}