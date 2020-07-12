package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.request.OrderAddReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hydra
 * @date 2020/7/11
 * @description: 订单模块控制层
 */
@Api(tags = "订单管理模块API接口")
@RestController
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @ApiOperation("新增订单")
    @PostMapping("/orders")
    public String newOrder(@RequestBody OrderAddReq orderAddReq){
        logger.warn(String.valueOf(orderAddReq.getOrderItemList().size()));

        if(orderAddReq.isManual()){
            return "isManual";
        }
        return "isAuto";
    }


}
