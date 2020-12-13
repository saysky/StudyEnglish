package com.example.mapper;

import com.example.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface GradeMapper {
    //根据ID查询一等级的信息
    Grade queryGradeById(Integer gradeId);
    //增加等级
    int addGrade(Grade grade);
    //删除等级
    int deleteGrade(Integer gradeId);
    //修改等级
    int updateGrade(Grade grade);
    //查看等级所有信息
    List<Grade> queryAllGrade();
}
