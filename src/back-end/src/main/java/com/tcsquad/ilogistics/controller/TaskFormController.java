package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.order.TaskForm;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.domain.request.TaskFormReq;
import com.tcsquad.ilogistics.service.TaskFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/taskforms")
public class TaskFormController {

    private static Logger logger = LoggerFactory.getLogger(TaskFormController.class);

    @Autowired//TO DO 改为构造注入
    private TaskFormService taskFormService;

    @GetMapping("")
    public List<TaskForm> getTaskFrom(TaskFormReq taskFormReq) { // no @RequestParam
        if (taskFormReq.getTaskFormId() != null) {
            return List.of(taskFormService.getTaskForm(taskFormReq.getTaskFormId()));
        } else if (taskFormReq.getQ() != null) {//TO DO 分页
            return taskFormService.searchTaskForms(taskFormReq.getQ());
        } else {//TO DO 分页
            return taskFormService.getAllTaskForms();
        }
    }

    @GetMapping("/{taskFormId}/transport")
    public Object getTransport(@PathVariable Long taskFormId) {
        return taskFormService.getTransport(taskFormId);
    }

    @GetMapping("/status")
    public Object getStatusOfAllTaskForm() {
        return taskFormService.getStatusOfAllTaskForms();
    }

    @GetMapping("/{taskFormId}/route")
    public Object getRoute(@PathVariable Long taskFormId) {
        return taskFormService.getRoute(taskFormId);
    }
}
