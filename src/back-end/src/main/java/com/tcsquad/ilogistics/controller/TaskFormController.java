package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.request.TaskFormReq;
import com.tcsquad.ilogistics.domain.response.RouteResp;
import com.tcsquad.ilogistics.domain.response.StatusStatisticsResp;
import com.tcsquad.ilogistics.domain.response.TaskFormLogResp;
import com.tcsquad.ilogistics.service.TaskFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskforms")
public class TaskFormController {

    private static Logger logger = LoggerFactory.getLogger(TaskFormController.class);

    @Autowired
    private TaskFormService taskFormService;

    @GetMapping("")
    public PageResult searchTaskFrom(TaskFormReq taskFormReq, PageRequest pageRequest) { // no @RequestParam
        if (taskFormReq.getQ() != null) {
            return taskFormService.searchTaskForms(taskFormReq.getQ(),pageRequest);
        } else {
            return taskFormService.getAllTaskForms(pageRequest);
        }
    }

    @GetMapping("/{taskFormId}")
    public TaskFormLogResp getTaskForm(@PathVariable Long taskFormId) {
        return taskFormService.toTaskFormLog(taskFormService.getTaskForm(taskFormId));
    }

    @GetMapping("/{taskFormId}/route")
    public RouteResp getRoute(@PathVariable Long taskFormId) {
        return taskFormService.getRoute(taskFormId);
    }

    @GetMapping("/status")
    public List<StatusStatisticsResp> countStatusOfAllTaskForms() {
        return taskFormService.countStatusOfAllTaskForms();
    }
}
