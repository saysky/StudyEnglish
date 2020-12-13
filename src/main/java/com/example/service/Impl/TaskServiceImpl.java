package com.example.service.Impl;

import com.example.mapper.TaskDetailMapper;
import com.example.mapper.TaskMapper;
import com.example.pojo.Task;
import com.example.pojo.TaskDetail;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskDetailMapper taskDetailMapper;

    @Override
    public Task queryTaskById(Integer taskId) {
        return taskMapper.queryTaskById(taskId);
    }

    @Override
    public int addTask(Task task) {
        return taskMapper.addTask(task);
    }

    @Override
    public int deleteTask(Integer taskId) {
        return taskMapper.deleteTask(taskId);
    }

    @Override
    public int updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    @Override
    public List<Task> queryAllTask() {
        return taskMapper.queryAllTask();
    }

    @Override
    public List<Task> findByUserId(Integer userId) {
        return taskMapper.findByUserId(userId);
    }


    @Override
    public Integer countByTask(Task task) {
        return taskDetailMapper.countByTask(task);
    }

    @Override
    public void addTaskDetail(TaskDetail taskDetail) {
        try {
            taskDetailMapper.addTaskDetail(taskDetail);
        } catch (Exception e) {

        }
    }

    @Override
    public void deleteTaskDetail(Integer taskId, Integer businessId) {
        taskDetailMapper.deleteTaskDetailByTaskIdAndBusinessId(taskId, businessId);
    }
}
