package com.example.demo.dao;

import com.example.demo.model.entity.Solution;
import com.example.demo.model.entity.SolutionExample;
import com.example.demo.model.entity.SolutionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SolutionMapper {
    int countByExample(SolutionExample example);

    int deleteByExample(SolutionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SolutionWithBLOBs record);

    int insertSelective(SolutionWithBLOBs record);

    List<SolutionWithBLOBs> selectByExampleWithBLOBs(SolutionExample example);

    List<Solution> selectByExample(SolutionExample example);

    SolutionWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SolutionWithBLOBs record, @Param("example") SolutionExample example);

    int updateByExampleWithBLOBs(@Param("record") SolutionWithBLOBs record, @Param("example") SolutionExample example);

    int updateByExample(@Param("record") Solution record, @Param("example") SolutionExample example);

    int updateByPrimaryKeySelective(SolutionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SolutionWithBLOBs record);

    int updateByPrimaryKey(Solution record);
}