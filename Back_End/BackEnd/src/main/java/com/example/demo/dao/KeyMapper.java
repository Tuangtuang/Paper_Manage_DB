package com.example.demo.dao;

import com.example.demo.model.entity.Key;
import com.example.demo.model.entity.KeyExample;
import com.example.demo.model.entity.KeyWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KeyMapper {
    int countByExample(KeyExample example);

    int deleteByExample(KeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KeyWithBLOBs record);

    int insertSelective(KeyWithBLOBs record);

    List<KeyWithBLOBs> selectByExampleWithBLOBs(KeyExample example);

    List<Key> selectByExample(KeyExample example);

    KeyWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KeyWithBLOBs record, @Param("example") KeyExample example);

    int updateByExampleWithBLOBs(@Param("record") KeyWithBLOBs record, @Param("example") KeyExample example);

    int updateByExample(@Param("record") Key record, @Param("example") KeyExample example);

    int updateByPrimaryKeySelective(KeyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(KeyWithBLOBs record);

    int updateByPrimaryKey(Key record);
}