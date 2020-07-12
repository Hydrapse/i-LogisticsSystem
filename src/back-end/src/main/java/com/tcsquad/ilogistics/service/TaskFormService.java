package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.order.Order;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.mapper.order.TaskFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<TaskForm> getAllTaskForms(){
        return taskFormMapper.getTaskForms();
    }

    public List<TaskForm> searchTaskForms(String[] keywords) {
        var res = new ArrayList<TaskForm>();
        if(keywords == null)
            return res;

        for(String keyword:keywords) {
            res.addAll(taskFormMapper.searchTaskForms(keyword));
        }
        return res;
    }

    public List<TaskForm> getTaskFormsBySubSiteId(Long subSiteId) {
        if(subSiteId == null)
            return new ArrayList<>();
        else
            return taskFormMapper.getTaskFormsBySubSiteId(subSiteId);
    }

    public List<TaskForm> generateTaskForms(Order order){
        return null;
    }

    //运输情况
    public Object getTransport(Long taskFormId) {
        return null;
    }

    public Object getStatusOfAllTaskForms() {
        return null;
    }

    public Object getRoute(Long taskFormId) {
        return null;
    }
}
