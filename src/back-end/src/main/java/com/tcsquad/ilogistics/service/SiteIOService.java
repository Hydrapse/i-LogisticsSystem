package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.storage.SiteIO;

public interface SiteIOService {
    //取消出入库记录（将status置为'F'）
    void cancelSiteIOStatus(String recordId);

    //出入库记录审核通过（将status置为'C'）
    void confirmSiteIORecord(String recordId);

    //新增入库记录
    void insertCheckinRecord(SiteIOAddReq siteIOAddReq);

    //新增出库记录
    void insertCheckOutRecord(SiteIO siteIO);
}
