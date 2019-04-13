package com.example.demo.dao;

import com.example.demo.model.entity.subject;
import com.example.demo.model.entity.subjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface subjectMapper {
    int countByExample(subjectExample example);

    int deleteByExample(subjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(subject record);

    int insertSelective(subject record);

    List<subject> selectByExample(subjectExample example);

    subject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") subject record, @Param("example") subjectExample example);

    int updateByExample(@Param("record") subject record, @Param("example") subjectExample example);

    int updateByPrimaryKeySelective(subject record);

    int updateByPrimaryKey(subject record);
}