package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.request.SubSiteTaskFormReq;
import com.tcsquad.ilogistics.service.SiteService;
import com.tcsquad.ilogistics.service.TaskFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subsites") //url为小写site
public class SubSiteController {
    @Autowired
    TaskFormService taskFormService;

    @Autowired
    SiteService siteService;

    @GetMapping("")
    public List<String> getAllSubSitesIds() {
        return siteService.getAllSubSite();
    }

    @GetMapping("/{subSiteId}/taskforms")
    public PageResult getTaskForms(@PathVariable String subSiteId, SubSiteTaskFormReq subSiteTaskFormReq, PageRequest pageRequest) {
        if (subSiteTaskFormReq.getStatus() != null) {
            return taskFormService.searchTaskFormsBySubSiteAndStatus(subSiteId,subSiteTaskFormReq.getStatus(),pageRequest);
        } else if (subSiteTaskFormReq.getQ() != null) {
            return taskFormService.searchTaskFormsBySubSiteAndKeyword(subSiteId,subSiteTaskFormReq.getQ(),pageRequest);
        } else {
            return taskFormService.getTaskFormsBySubSiteId(subSiteId,pageRequest);
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
