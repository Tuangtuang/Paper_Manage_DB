package com.example.demo.dao;

import com.example.demo.model.entity.key;
import com.example.demo.model.entity.keyExample;
import com.example.demo.model.entity.keyWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface keyMapper {
    int countByExample(keyExample example);

    int deleteByExample(keyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(keyWithBLOBs record);

    int insertSelective(keyWithBLOBs record);

    List<keyWithBLOBs> selectByExampleWithBLOBs(keyExample example);

    List<key> selectByExample(keyExample example);

    keyWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") keyWithBLOBs record, @Param("example") keyExample example);

    int updateByExampleWithBLOBs(@Param("record") keyWithBLOBs record, @Param("example") keyExample example);

    int updateByExample(@Param("record") key record, @Param("example") keyExample example);

    int updateByPrimaryKeySelective(keyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(keyWithBLOBs record);

    int updateByPrimaryKey(key record);
}