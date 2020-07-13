package com.tcsquad.ilogistics.service;

import java.util.List;

public interface WarehouseService {

    List<String> getWarehouseIdsByItemAndMainsite(String itemId, String mainsiteId);

    List<String> getWarehouseOptionsToCheckout(String itemId,int itemNum,String mainsiteId);

    List<String> getWarehouseOptionsToCheckin(String itemId,int itemNum,String mainsiteId);

    //向库房中添加商品
    void addItemToWarehouse(String warehouseId,String itemId,int itemNum);

    //从库房中出库
    void decreaseItemOfWarehouse(String warehouseId,String itemId,int itemNum);
}
