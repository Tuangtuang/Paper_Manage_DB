package com.example.demo.dao;

import com.example.demo.model.entity.second_knowledge;
import com.example.demo.model.entity.second_knowledgeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface second_knowledgeMapper {
    int countByExample(second_knowledgeExample example);

    int deleteByExample(second_knowledgeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(second_knowledge record);

    int insertSelective(second_knowledge record);

    List<second_knowledge> selectByExample(second_knowledgeExample example);

    second_knowledge selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") second_knowledge record, @Param("example") second_knowledgeExample example);

    int updateByExample(@Param("record") second_knowledge record, @Param("example") second_knowledgeExample example);

    int updateByPrimaryKeySelective(second_knowledge record);

    int updateByPrimaryKey(second_knowledge record);
}