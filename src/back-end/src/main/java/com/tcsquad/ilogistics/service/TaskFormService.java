package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskFormService {

    @Autowired
    TaskFormMapper taskFormMapper;

    public TaskForm getTaskForm(Long taskFormId){
        if(taskFormId != null)
            return taskFormMapper.getTaskForm(taskFormId);
        else
            throw new BusinessErrorException("任务单id不能为空", ErrorCode.MISS_PARAMS.getCode());
    }

    public TaskForm[] getAllTaskForm(){
        return new TaskForm[]{};
    }

    public TaskForm[] searchTaskForms(String[] keywords) {
        return new TaskForm[]{};
    }

    public TaskForm[] getTaskFormBySubSiteId(Long subSiteId) {
        return new TaskForm[]{};
    }

    public TaskForm[] generateTaskForms(Order order){
        return null;
    }

    public Object getTransport(Long taskFormId) {
        return null;
    }

    public Object getStatusOfAllTaskForm() {
        return null;
    }

    public Object getRoute(Long taskFormId) {
        return null;
    }
}
