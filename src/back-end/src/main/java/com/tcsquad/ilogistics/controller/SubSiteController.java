package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.request.SubSiteTaskFormReq;
import com.tcsquad.ilogistics.domain.response.TaskFormLogResp;
import com.tcsquad.ilogistics.service.TaskFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subsites") //url为小写site
public class SubSiteController {
    @Autowired
    TaskFormService taskFormService;

    @GetMapping("/{subSiteId}/taskforms")
    public List<TaskFormLogResp> getTaskForms(@PathVariable String subSiteId, SubSiteTaskFormReq subSiteTaskFormReq) {
        if (subSiteTaskFormReq.getStatus() != null) {
            return taskFormService.toTaskFormLogs(taskFormService.searchTaskFormsBySubSiteAndStatus(subSiteId,subSiteTaskFormReq.getStatus()));
        } else if (subSiteTaskFormReq.getQ() != null) {
            return taskFormService.toTaskFormLogs(taskFormService.searchTaskFormsBySubSiteAndKeyword(subSiteId,subSiteTaskFormReq.getQ()));
        } else {
            return taskFormService.toTaskFormLogs(taskFormService.getTaskFormsBySubSiteId(subSiteId));
        }
    }

    @Deprecated
    @DeleteMapping("/{subSiteId}/inventory/items")
    public Object takeItems(@PathVariable Long subSiteId, Object object) { // no @RequestParam
        return null;
    }

    @Deprecated
    @PostMapping("/{subSiteId}/inventory/items")
    public Object addItems(@PathVariable Long subSiteId, @RequestBody Object object) {
        return null;
    }
}
