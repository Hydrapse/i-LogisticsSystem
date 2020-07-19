package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.Category;
import com.tcsquad.ilogistics.domain.storage.Inventory;
import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.domain.response.WarehouseResp;
import com.tcsquad.ilogistics.domain.storage.MainsiteInventory;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WarehouseMapper {

    //根据主站编号获取库房统计信息(库房的item种类个数，以及库房当前存储总量)
    List<WarehouseResp> getWarehouseInfoListByMainsiteId(String mainsiteId);

    //根据主站编号获取库房列表
    Category getCategoryByWarehouseId(String warehouseId);

    //根据库房编号获取货物列表
    List<Item> getItemListByWarehouseId(String warehouseId);

    //根据库房编号获取货物库存（这里设置resultType="map"，这里可能会出现一点问题）
    @MapKey("itemId")
    List<Map<String,Integer>> getItemsInventoryByWarehouseId(String warehouseId);

    //更新库房库存
    void updateInventoryByWarehouseIdAndItemId(Inventory inventory);

    //根据主站编号和商品编号获取商品所在的库房
    List<String> getWarehouseIdsByItemAndMainsite(String itemId,String mainsiteId);

    //根据主站编号和商品编号和商品数量获取商品可出库的库房
    List<String> getWarehouseOptionsToCheckout(@Param("itemId")String itemId, @Param("itemNum")int itemNum, @Param("mainsiteId")String mainsiteId);

    //根据主站编号和商品编号和商品数量获取商品可入库的库房
    List<String> getWarehouseOptionsToCheckin(@Param("itemId")String itemId, @Param("itemNum")int itemNum, @Param("mainsiteId")String mainsiteId);

    //原本该库房为存储该商品，现新增该商品，即在数据库中新增一行
    void insertInventoryOfItem(Inventory inventory);

    //根据库房编号和商品编号获取库存
    Inventory getInventoryByItemIdAndWarehouseId(@Param("warehouseId")String warehouseId,@Param("itemId")String itemId);

    //根据商品编号获取商品在各主站的库存
    List<MainsiteInventory> getMainsiteInventorysByItemId(String itemId);

    List<Inventory> getInventoryListByItemAndMainsite(@Param("itemId")String itemId,@Param("mainsiteId")String mainsiteId);

}
