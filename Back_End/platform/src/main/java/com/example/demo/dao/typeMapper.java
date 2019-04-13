package com.example.demo.dao;

import com.example.demo.model.entity.type;
import com.example.demo.model.entity.typeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface typeMapper {
    int countByExample(typeExample example);

    int deleteByExample(typeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(type record);

    int insertSelective(type record);

    List<type> selectByExample(typeExample example);

    type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") type record, @Param("example") typeExample example);

    int updateByExample(@Param("record") type record, @Param("example") typeExample example);

    int updateByPrimaryKeySelective(type record);

    int updateByPrimaryKey(type record);
}