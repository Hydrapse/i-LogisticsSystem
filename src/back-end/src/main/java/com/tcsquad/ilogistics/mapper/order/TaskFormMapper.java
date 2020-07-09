package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.TaskForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskFormMapper {

    //通过订单Id查询该订单对应的任务单
    List<TaskForm> getTaskFormsByOrderId(int orderId);

    //List<TaskForm> getTaskForms();

    //根据任务单编号查询任务单
    TaskForm getTaskForm(int taskId);

    void insertTaskForm(TaskForm taskForm);

    void updateTaskFormStatus(String status,int taskId);

    void deleteTaskForm(int taskId);

    //删除某一订单对应的任务单
    void deleteTaskFormsByOrderId(int orderId);

}
