package com.tcsquad.ilogistics.mapper.storage;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicInventoryMapper {

    //根据商品编号和主站获取逻辑库存
    int getLogicInventoryByMainsiteAndItemId(@Param("mainsiteId") String mainsiteId,@Param("itemId") String itemId);

    //更新逻辑库存
    boolean updateLogicInventory(@Param("mainsiteId") String mainsiteId,@Param("itemId") String itemId,@Param("logicInv") int logicInv);
}
