package com.tcsquad.ilogistics.mapper.order;

import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.domain.storage.SubSite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskFormMapper {

    //通过订单Id查询该订单对应的任务单
    List<TaskForm> getTaskFormsByOrderId(long orderId);

    //通过配送站Id查询该订单对应的任务单
    List<TaskForm> getTaskFormsBySubSiteId(String subsiteId); //DONE

    //获取所有任务单
    List<TaskForm> getTaskForms(); //DONE

    //按关键词搜索任务单
    List<TaskForm> searchTaskForms(String[] keyword); //TODO

    //根据任务单编号查询任务单
    TaskForm getTaskForm(long taskId);

    //根据任务单status查询任务单
    List<TaskForm> getTaskFormsByStatus(String status);

    //根据status统计任务单数量
    int countTaskFormsByStatus(String status); //TODO

    //根据配送站和关键词查询任务单
//    List<TaskForm> getTaskFormsBySubsiteAndKeyword(@Param("subSite")SubSite subSite,@Param("keyword")String keyword);//OLD
    List<TaskForm> getTaskFormsBySubsiteAndKeyword(String subSiteId,@Param("keyword")String[] keyword);//TODO

    //根据配送站和状态查询任务单
//    List<TaskForm> getTaskFormsBySubsiteAndStatus(@Param("subSite")SubSite subSite,@Param("status")String status);//OLD
    List<TaskForm> getTaskFormsBySubsiteAndStatus(String subSiteId,@Param("status")String status);


    void insertTaskForm(TaskForm taskForm);

    void updateTaskFormStatus(String status,long taskId);

    void deleteTaskForm(long taskId);

    //删除某一订单对应的任务单
    void deleteTaskFormsByOrderId(long orderId);

}
