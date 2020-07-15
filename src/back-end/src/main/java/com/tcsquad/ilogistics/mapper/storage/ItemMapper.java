package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.response.ItemInventoryResp;
import com.tcsquad.ilogistics.domain.storage.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {

    //通过商品编号获取item列表信息
    List<Item> getItemListByCategoryId(String categoryId);

    List<ItemInventoryResp> getItemInventoryByWarehouseId(String warehouseId);

    Item getItem(String itemId);

    //新增
    void insertItem(Item item);

    //更新商品状态（上架/下架）
    void updateItemStatus(Item item);

    //更新
    void updateItem(Item item);

}
