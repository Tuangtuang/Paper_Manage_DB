package com.example.demo.dao;

import com.example.demo.model.entity.school;
import com.example.demo.model.entity.schoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface schoolMapper {
    int countByExample(schoolExample example);

    int deleteByExample(schoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(school record);

    int insertSelective(school record);

    List<school> selectByExample(schoolExample example);

    school selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") school record, @Param("example") schoolExample example);

    int updateByExample(@Param("record") school record, @Param("example") schoolExample example);

    int updateByPrimaryKeySelective(school record);

    int updateByPrimaryKey(school record);
}