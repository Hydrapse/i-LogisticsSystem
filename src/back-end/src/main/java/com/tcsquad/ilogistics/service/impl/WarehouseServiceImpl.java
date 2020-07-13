package com.tcsquad.ilogistics.service.impl;

import com.tcsquad.ilogistics.domain.storage.Inventory;
import com.tcsquad.ilogistics.mapper.storage.WarehouseMapper;
import com.tcsquad.ilogistics.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public List<String> getWarehouseIdsByItemAndMainsite(String itemId, String mainsiteId) {
        return warehouseMapper.getWarehouseIdsByItemAndMainsite(itemId,mainsiteId);
    }

    @Override
    public List<String> getWarehouseOptionsToCheckout(String itemId, int itemNum, String mainsiteId) {
        return warehouseMapper.getWarehouseOptionsToCheckout(itemId,itemNum,mainsiteId);
    }

    @Override
    public List<String> getWarehouseOptionsToCheckin(String itemId, int itemNum, String mainsiteId) {
        return warehouseMapper.getWarehouseOptionsToCheckin(itemId,itemNum,mainsiteId);
    }

    @Override
    public void addItemToWarehouse(String warehouseId, String itemId, int itemNum) {
        Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(warehouseId,itemId);
        if(inventory == null){
            inventory = new Inventory();
            inventory.setWarehouseId(warehouseId);
            inventory.setItemId(itemId);
            inventory.setItemNum(itemNum);
            warehouseMapper.insertInventoryOfItem(inventory);
        }
        else{
            int num = inventory.getItemNum() + itemNum;
            inventory.setItemNum(num);
            warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);
        }
    }

    @Override
    public void decreaseItemOfWarehouse(String warehouseId, String itemId, int itemNum) {
        Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(warehouseId,itemId);
        int num = inventory.getItemNum() - itemNum;
        inventory.setItemNum(num);
        warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);
    }
}
