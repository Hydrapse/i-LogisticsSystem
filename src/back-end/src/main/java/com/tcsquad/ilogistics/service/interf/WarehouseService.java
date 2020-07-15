package com.tcsquad.ilogistics.service.interf;

import com.tcsquad.ilogistics.domain.request.InventoryUpdateReq;
import com.tcsquad.ilogistics.domain.response.WarehouseDetailResp;
import com.tcsquad.ilogistics.domain.response.WarehouseResp;

import java.util.List;

public interface WarehouseService {

    List<String> getWarehouseIdsByItemAndMainsite(String itemId, String mainsiteId);

    List<String> getWarehouseOptionsToCheckout(String itemId,int itemNum,String mainsiteId);

    List<String> getWarehouseOptionsToCheckin(String itemId,int itemNum,String mainsiteId);

    //向库房中添加商品
    void addItemToWarehouse(String warehouseId,String itemId,int itemNum);

    //从库房中出库
    void decreaseItemOfWarehouse(String warehouseId,String itemId,int itemNum);

    //获取库房简要信息列表
    List<WarehouseResp> getAllWarehouseInfo(String mainsiteId);

    //获取库房详细信息
    WarehouseDetailResp getWarehouseDetail(String warehouseId,String mainsiteId);

    //在库房间转移货物
    void updateItemInventoryBetweenWarehouses(InventoryUpdateReq req);
}
