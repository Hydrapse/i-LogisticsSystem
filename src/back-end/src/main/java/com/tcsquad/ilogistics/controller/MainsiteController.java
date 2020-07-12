package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.service.SiteIOService;
import com.tcsquad.ilogistics.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "主站管理模块API接口")
@RestController
@RequestMapping("/mainsites")
public class MainsiteController {

    @Autowired
    SiteIOService siteIOService;
    @Autowired
    WarehouseService warehouseService;

    //入库请求
    @ApiOperation("入库请求")
    @GetMapping("/{mainsiteId}/inventory/inquery")
    public void insertCheckinRecord(@PathVariable("mainsiteId")String mainsiteId, SiteIOAddReq siteIOAddReq){
        List<String> warehouseList = warehouseService.getWarehouseIdsByItemAndMainsite(siteIOAddReq.getItemId(),mainsiteId);

        //这里将该商品存放在第一个仓库
        //Todo:之后应该要加上一些策略配置
        siteIOAddReq.setWarehouseId(warehouseList.get(0));
        siteIOService.insertCheckinRecord(siteIOAddReq);
    }


}
