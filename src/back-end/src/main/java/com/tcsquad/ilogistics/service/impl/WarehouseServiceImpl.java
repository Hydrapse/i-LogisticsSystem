package com.tcsquad.ilogistics.service.impl;

import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.request.InventoryUpdateReq;
import com.tcsquad.ilogistics.domain.response.ItemInventoryResp;
import com.tcsquad.ilogistics.domain.response.WarehouseDetailResp;
import com.tcsquad.ilogistics.domain.response.WarehouseResp;
import com.tcsquad.ilogistics.domain.storage.Category;
import com.tcsquad.ilogistics.domain.storage.Inventory;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
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


    @Override
    @Transactional
    public void updateItemInventoryBetweenWarehouses(InventoryUpdateReq req) {
        //查询该商品是否可以存放在目标库房中
        Inventory inventoryDest = warehouseMapper.getInventoryByItemIdAndWarehouseId(req.getDestWarehouseId(),req.getItemId());
        if(inventoryDest == null){
            throw new BusinessErrorException("业务逻辑异常, 目标库房不能存储该商品",
                    ErrorCode.ORDER_ALREADY_SUBMIT.getCode());
        }
        else{
            //减掉源库房的库存
            Inventory inventorySrc = warehouseMapper.getInventoryByItemIdAndWarehouseId(req.getSourceWarehouseId(),req.getItemId());
            int realNum = inventorySrc.getItemNum() - req.getItemNum();
            int logicNum = inventorySrc.getLogicInventory() - req.getItemNum();
            inventorySrc.setItemNum(realNum);
            inventorySrc.setLogicInventory(logicNum);
            warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventorySrc);

            //添加目标库房的库存
            realNum = inventoryDest.getItemNum() + req.getItemNum();
            logicNum = inventoryDest.getLogicInventory() + req.getItemNum();
            inventoryDest.setItemNum(realNum);
            inventoryDest.setLogicInventory(logicNum);
            warehouseMapper.updateInventoryByWarehouseIdAndItemId(inventoryDest);
        }

    }
}
