package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.domain.response.WarehouseResp;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WarehouseMapper {
    //根据主站编号获取库房列表
    List<WarehouseResp> getWarehouseRespByMainSiteId(String mainSiteId);

    //根据库房编号获取货物列表
    List<Item> getItemListByWarehouseId(String warehouseId);

    //根据库房编号获取货物库存（这里设置resultType="map"，这里可能会出现一点问题）
    @MapKey("itemId")
    Map<String,Integer> getItemsInventoryByWarehouseId(String warehouseId);

}
