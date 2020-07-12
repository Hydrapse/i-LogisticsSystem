package com.tcsquad.ilogistics.mapper.clientele;

import com.tcsquad.ilogistics.domain.clientele.SupplyIO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyIOMapper {

    void insertSupplyIO(SupplyIO supplyIO);

    //通过记录号查询与供应商的退货订货记录
    SupplyIO getSupplyIOByRecordId(long recordId);

    //通过供应商编号查询退订货记录
    List<SupplyIO> getSupplyIOListBySupplierId(String supplierId);

    //通过主站编号查询与供应商的退订货记录
    List<SupplyIO> getSupplyIOListByMainId(String mainSiteId);

    //通过商品编号查询与供应商的退订货记录
    List<SupplyIO> getSupplyIOListByItemId(String itemId);


}
