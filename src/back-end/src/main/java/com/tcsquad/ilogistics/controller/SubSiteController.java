package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.order.TaskForm;
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
    public List<TaskForm> getTaskForms(@PathVariable Long subSiteId) {
        return taskFormService.getTaskFormsBySubSiteId(subSiteId);
    }

    @DeleteMapping("/{subSiteId}/inventory/items")
    public Object takeItems(@PathVariable Long subSiteId,Object object) { // no @RequestParam
        return null;
    }

    @PostMapping("/{subSiteId}/inventory/items")
    public Object addItems(@PathVariable Long subSiteId,@RequestBody Object object) {
        return null;
    }
}