package com.tcsquad.ilogistics.service.impl;

import com.tcsquad.ilogistics.domain.response.ItemInventoryResp;
import com.tcsquad.ilogistics.domain.response.WarehouseDetailResp;
import com.tcsquad.ilogistics.domain.response.WarehouseResp;
import com.tcsquad.ilogistics.domain.storage.Category;
import com.tcsquad.ilogistics.domain.storage.Inventory;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import com.tcsquad.ilogistics.mapper.storage.WarehouseMapper;
import com.tcsquad.ilogistics.service.interf.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;
    @Autowired
    ItemMapper itemMapper;

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
    @Transactional
    public void addItemToWarehouse(String warehouseId, String itemId, int itemNum) {
        System.out.println("addItemToWarehouse");
        Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(warehouseId,itemId);
        if(inventory == null){
            inventory = new Inventory();
            inventory.setWarehouseId(warehouseId);
            inventory.setItemId(itemId);
            inventory.setItemNum(itemNum);
            inventory.setLogicInventory(itemNum);
            warehouseMapper.insertInventoryOfItem(inventory);
        }
        else{
            int num = inventory.getItemNum() + itemNum;
            inventory.setItemNum(num);
            num = inventory.getLogicInventory() + itemNum;
            inventory.setLogicInventory(num);
            warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);
        }
    }

    @Override
    @Transactional
    public void decreaseItemOfWarehouse(String warehouseId, String itemId, int itemNum) {
        Inventory inventory = warehouseMapper.getInventoryByItemIdAndWarehouseId(warehouseId,itemId);
        int num = inventory.getItemNum() - itemNum;
        inventory.setItemNum(num);
        warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventory);
    }

    @Override
    public List<WarehouseResp> getAllWarehouseInfo(String mainsiteId) {
        List <WarehouseResp> warehouseRespList = warehouseMapper.getWarehouseInfoListByMainsiteId(mainsiteId);
        for (WarehouseResp w:warehouseRespList
             ) {
            Category category = warehouseMapper.getCategoryByWarehouseId(w.getWarehouseId());
            w.setCategory(category);
            w.setCategoryId(category.getCategoryId());
            w.setSiteId(mainsiteId);
        }
        return warehouseRespList;
    }

    @Override
    public WarehouseDetailResp getWarehouseDetail(String warehouseId, String mainsiteId) {
        WarehouseDetailResp warehouseDetailResp = new WarehouseDetailResp();
        warehouseDetailResp.setMainSiteId(mainsiteId);
        warehouseDetailResp.setMainsiteName(warehouseId);
        warehouseDetailResp.setWarehouseId(warehouseId);
        Category category = warehouseMapper.getCategoryByWarehouseId(warehouseId);
        warehouseDetailResp.setCategory(category);
        List<ItemInventoryResp> itemInventoryResps = itemMapper.getItemInventoryByWarehouseId(warehouseId);
        warehouseDetailResp.setItemList(itemInventoryResps);
        return warehouseDetailResp;
    }
}
