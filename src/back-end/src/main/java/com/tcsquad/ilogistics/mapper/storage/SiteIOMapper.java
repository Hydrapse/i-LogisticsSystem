package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.SiteIO;

public interface SiteIOMapper {
    //新增出入库记录
    void insertSiteIORecord(SiteIO siteIO);

    //修改出入库记录状态
    void updateSiteIOStatus(String recordId,String status);

    //查询出入库记录
    SiteIO getSiteIORecordById(String recordId);

}
