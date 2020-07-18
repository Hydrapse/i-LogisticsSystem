package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.response.TransferResp;
import com.tcsquad.ilogistics.mapper.storage.AdjustFormMapper;
import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    AdjustFormMapper adjustFormMapper;

    @Autowired
    SiteMapper siteMapper;

    @GetMapping("/transfer/{mainSiteId}")
    public List<TransferResp> getTransfer(@PathVariable String mainSiteId) {
        var res = new ArrayList<TransferResp>();
        var results = adjustFormMapper.getAdjustFormsByFromId(mainSiteId);
        for(var result:results) {
            var tmp = new TransferResp();
            tmp.setAdjustId(result.getAdjustId());
            tmp.setItemId(result.getItemId());
            tmp.setItemNum(result.getItemNum());
            tmp.setFrom(result.getToMainSiteId());
            tmp.setTo(result.getFromMainSiteId());
            if(result.getAdjustStatus().equals("N"))//to未处理（N）to已处理（D）已到达（R）
                tmp.setStatus( result.getToMainSiteId()+"未处理");
            else if(result.getAdjustStatus().equals("D"))
                tmp.setStatus("已处理");
            else if(result.getAdjustStatus().equals("R"))
                tmp.setStatus("已到达"+result.getFromMainSiteId());
            else
                tmp.setStatus("状态有误");

            var fromSite = siteMapper.getMainSiteById(result.getToMainSiteId());
            var toSite = siteMapper.getMainSiteById(result.getFromMainSiteId());
            tmp.setFromPoint(new TransferResp.Point(fromSite.getLatitude().doubleValue(),fromSite.getLongitude().doubleValue()));
            tmp.setToPoint(new TransferResp.Point(toSite.getLatitude().doubleValue(),toSite.getLongitude().doubleValue()));
            res.add(tmp);
        }
        return res;
    }
}
