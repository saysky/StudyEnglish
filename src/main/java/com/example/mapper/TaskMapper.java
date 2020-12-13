package com.example.mapper;

import com.example.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskMapper {
    //根据ID查询一条进度的信息
    Task queryTaskById(Integer taskId);
    //增加进度
    int addTask(Task task);
    //删除进度
    int deleteTask(Integer taskId);
    //修改进度
    int updateTask(Task task);
    //查看进度所有信息
    List<Task> queryAllTask();

    List<Task> findByUserId(Integer userId);



}
