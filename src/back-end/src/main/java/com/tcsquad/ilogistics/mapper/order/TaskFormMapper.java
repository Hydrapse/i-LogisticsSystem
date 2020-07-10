package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.TaskForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskFormMapper {

    //通过订单Id查询该订单对应的任务单
    List<TaskForm> getTaskFormsByOrderId(long orderId);

    //List<TaskForm> getTaskForms();

    //根据任务单编号查询任务单
    TaskForm getTaskForm(long taskId);

    void insertTaskForm(TaskForm taskForm);

    void updateTaskFormStatus(String status,long taskId);

    void deleteTaskForm(long taskId);

    //删除某一订单对应的任务单
    void deleteTaskFormsByOrderId(long orderId);

}
