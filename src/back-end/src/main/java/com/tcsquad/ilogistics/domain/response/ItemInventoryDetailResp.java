package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.storage.Item;
import com.tcsquad.ilogistics.domain.storage.MainsiteInventory;
import lombok.Data;

import java.util.List;

@Data
public class ItemInventoryDetailResp {
    Item item;
    Integer totalInventory;
    List<MainsiteInventory> mainsiteInventoryList;
}
