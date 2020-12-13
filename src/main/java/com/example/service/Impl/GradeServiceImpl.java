package com.example.service.Impl;

import com.example.mapper.GradeMapper;
import com.example.pojo.Grade;
import com.example.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

    @Override
    public Grade queryGradeById(Integer gradeId) {
        return gradeMapper.queryGradeById(gradeId);
    }

    @Override
    public int addGrade(Grade grade) {
        return gradeMapper.addGrade(grade);
    }

    @Override
    public int deleteGrade(Integer gradeId) {
        return gradeMapper.deleteGrade(gradeId);
    }

    @Override
    public int updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade);
    }

    @Override
    public List<Grade> queryAllGrade() {
        return gradeMapper.queryAllGrade();
    }
}
