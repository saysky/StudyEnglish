package com.example.service;

import com.example.pojo.Grade;
import com.example.pojo.Listen;
import com.example.pojo.Word;

import java.util.List;

public interface ListenService {
    //根据ID查询一条听力的信息
    Listen queryListenById(Integer listenId);
    //增加一条听力
    int addListen(Listen listen);
    //删除一条听力
    int deleteListen(Integer listenId);
    //修改一条听力
    int updateListen(Listen listen);
    //查看所有听力所有信息
    List<Listen> queryAllListen();

    Integer countByGrade(Integer grade);

    Listen getNext(Integer taskId);

    List<Grade> getAllGrade();

}
