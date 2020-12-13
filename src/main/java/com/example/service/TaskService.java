package com.example.service;

import com.example.pojo.Task;
import com.example.pojo.TaskDetail;

import java.util.List;

public interface TaskService {
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

    Integer countByTask(Task task);

    /**
     * 添加
     * @param taskDetail
     */
    void addTaskDetail(TaskDetail taskDetail);


    /**
     * 删除
     * @param taskId
     * @param businessId
     */
    void deleteTaskDetail(Integer taskId, Integer businessId);
}
