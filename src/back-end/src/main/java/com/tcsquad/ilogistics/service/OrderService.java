package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.domain.request.OrderAddReq;
import com.tcsquad.ilogistics.mapper.order.OrderMapper;
import com.tcsquad.ilogistics.mapper.storage.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hydra
 * @date 2020/7/13
 * @description:
 */

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ItemMapper itemMapper;

    /**
     * 功能描述: 完善OrderAddReq详细信息
     */
    public void constructOrderReq(OrderAddReq orderAddReq){
        
    }
}
