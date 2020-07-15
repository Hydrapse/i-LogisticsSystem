package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.storage.Item;
import lombok.Data;

@Data
public class ItemInventoryResp{
    Item item;
    Integer inventory;
}
