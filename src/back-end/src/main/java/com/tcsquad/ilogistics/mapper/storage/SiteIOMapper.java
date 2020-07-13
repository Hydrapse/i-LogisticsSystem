package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.SiteIO;

public interface SiteIOMapper {
    //新增出入库记录
    void insertSiteIORecord(SiteIO siteIO);

    //修改出入库记录状态
    void updateSiteIOStatus(Long recordId,String status);

    //查询出入库记录
    SiteIO getSiteIORecordById(Long recordId);

    //根据formId、itemId和type来查询recordId
    Long getSiteIORecordIdByFormIdAndItemId(String type, Long formId,String itemId);

    //修改出入库库房
    void updateSiteIOWarehouseId(Long recordId,String warehouseId);

}
