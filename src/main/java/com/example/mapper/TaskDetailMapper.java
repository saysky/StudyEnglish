package com.example.mapper;

import com.example.pojo.Task;
import com.example.pojo.TaskDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskDetailMapper {
    //根据ID查询一条进度详情的信息
    TaskDetail queryTaskDetailById(Integer taskDetailId);

    //增加进度详情
    int addTaskDetail(TaskDetail taskDetail);

    //删除进度详情
    int deleteTaskDetail(Integer taskDetailId);

    //修改进度详情
    int updateTaskDetail(TaskDetail taskDetail);

    //查看进度详情所有信息
    List<TaskDetail> queryAllTaskDetail();

    Integer countByTask(Task task);



    /**
     * 删除
     * @param taskId
     * @param businessId
     */
    void deleteTaskDetailByTaskIdAndBusinessId(@Param("taskId") Integer taskId,
                                               @Param("businessId") Integer businessId);
}
