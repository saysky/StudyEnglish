package com.example.service.Impl;

import com.example.mapper.ListenMapper;
import com.example.pojo.Grade;
import com.example.pojo.Listen;
import com.example.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListenServiceImpl implements ListenService {
    @Autowired
    ListenMapper listenMapper;

    @Override
    public Listen queryListenById(Integer listenId) {
        return listenMapper.queryListenById(listenId);
    }

    @Override
    public int addListen(Listen listen) {
        return listenMapper.addListen(listen);
    }

    @Override
    public int deleteListen(Integer listenId) {
        return listenMapper.deleteListen(listenId);
    }

    @Override
    public int updateListen(Listen listen) {
        return listenMapper.updateListen(listen);
    }

    @Override
    public List<Listen> queryAllListen() {
        return listenMapper.queryAllListen();
    }

    @Override
    public Integer countByGrade(Integer grade) {
        return listenMapper.countByGrade(grade);
    }

    @Override
    public Listen getNext(Integer taskId) {
        return listenMapper.getNext(taskId);
    }

    @Override
    public List<Grade> getAllGrade() {
        return listenMapper.getAllGrade();
    }
}
