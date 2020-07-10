package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {
    //获取当前item的库存
    //int getInventoryByWarehouseIdAndItemID(@Param("itemId")String itemId);

    //更新主站库房库存：待修改
    void updateInventory(@Param("itemId")String itemId,@Param("increment")int increment,@Param("warehouseId")String warehouseId);

    //通过二级商品编号获取item列表信息
    List<Item> getItemListByCategoryId(String categoryId);

    Item getItem(String itemId);

    //上架
    void insertItem(Item item);

    //删除
    void deleteItemByItemId(String itemId);

    //更新 oldItemId是修改前Item的id
    void updateItem(String oldItemId,Item item);

}
