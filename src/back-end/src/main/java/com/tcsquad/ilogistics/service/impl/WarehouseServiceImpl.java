package com.tcsquad.ilogistics.service.impl;

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


}
