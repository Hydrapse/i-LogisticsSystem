package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.SiteIO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteIOMapper {
    //新增出入库记录
    void insertSiteIORecord(SiteIO siteIO);

    //修改出入库记录状态
    void updateSiteIOStatus(@Param("recordId")Long recordId,@Param("status")String status);

    //查询出入库记录
    SiteIO getSiteIORecordById(Long recordId);

    //根据formId、itemId和type来查询recordId
    Long getSiteIORecordIdByFormIdAndItemId(@Param("type")String type,@Param("formId")Long formId,@Param("itemId")String itemId);

    //修改出入库库房
    void updateSiteIOWarehouseId(@Param("recordId")Long recordId,@Param("warehouseId")String warehouseId);

}
