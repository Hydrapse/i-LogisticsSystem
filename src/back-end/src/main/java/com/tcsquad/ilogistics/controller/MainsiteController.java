package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.response.*;
import com.tcsquad.ilogistics.service.interf.SiteIOService;
import com.tcsquad.ilogistics.service.interf.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "主站管理模块API接口")
@RestController
@RequestMapping("/mainsites")
public class MainsiteController {

    @Autowired
    SiteIOService siteIOService;
    @Autowired
    WarehouseService warehouseService;

    @ApiOperation("入库请求")
    @PostMapping("/{mainsiteId}/inventory/inquery")
    public void insertCheckinRecord(@PathVariable("mainsiteId")String mainsiteId,
                                    @RequestBody SiteIOAddReq siteIOAddReq){
        List<String> warehouseList = warehouseService.getWarehouseOptionsToCheckin(siteIOAddReq.getItemId(),siteIOAddReq.getItemNum(),mainsiteId);
        if(warehouseList.isEmpty()){
            //根据itemId和mainsiteId未能找到相匹配库房，入库请求失败
            //Todo: Error

            return;
        }
        //这里将该商品存放在第一个仓库
        //Todo:之后应该要加上一些策略配置
        siteIOAddReq.setWarehouseId(warehouseList.get(0));
        Long newRecordId = siteIOService.insertCheckinRecord(siteIOAddReq);

        //Todo:向消息队列推送消息

    }

    @ApiOperation("获取入库消息")
    @GetMapping("/{mainsiteId}/inventory/inmessages")
    public ItemCheckinResp getItemCheckinResp(Long recordId){
        ItemCheckinResp itemCheckinResp = siteIOService.getItemCheckinRespByRecordId(recordId);
        return itemCheckinResp;
    }

    @ApiOperation("查询入库单详细信息")
    @GetMapping("/{mainsiteId}/inventory/sitein/{recordId}")
    public SiteIOCheckInResp getSiteIOCheckinDetail(@PathVariable("mainsiteId")String mainsiteId,
                                                    @PathVariable("recordId")Long recordId){
        SiteIOCheckInResp siteIOCheckInResp = siteIOService.getSiteIOCheckinRespByRecordId(recordId,mainsiteId);
        return siteIOCheckInResp;
    }

    @ApiOperation("修改入库单信息")
    @PatchMapping("/{mainsiteId}/inventory/sitein/{recordId}")
    public String updateSiteIOCheckinInfo(@PathVariable("mainsiteId")String mainsiteId,
                                          @PathVariable("recordId")Long recordId,
                                          String warehouseId,String approvalStatus){
        /*
        1. 修改入库仓库信息
        2. 修改入库状态，其中包含对其他模块的调用（例如调货单状态的修改）
         */
        siteIOService.updateWarehouseToCheckin(recordId,warehouseId);
        if(approvalStatus.equals(StatusString.CONFIRM.getValue())){ //确认审核
            siteIOService.confirmSiteIORecord(recordId);
        }
        else if(approvalStatus.equals(StatusString.INVALID.getValue())) { //取消审核
            siteIOService.cancelSiteIOStatus(recordId);
        }
        else{
            //Todo:Error
        }
        return "Success";
    }

    @ApiOperation("获取出库消息")
    @GetMapping("/{mainsiteId}/inventory/outmessages")
    public ItemCheckoutResp getItemCheckoutResp(Long recordId){
        ItemCheckoutResp itemCheckoutResp = siteIOService.getItemCheckoutRespByRecordId(recordId);
        return itemCheckoutResp;
    }

    @ApiOperation("查询出库单详细信息")
    @GetMapping("/{mainsiteId}/inventory/siteout/{recordId}")
    public SiteIOCheckoutResp getSiteIOCheckoutDetail(@PathVariable("mainsiteId")String mainsiteId,
                                                      @PathVariable("recordId")Long recordId){
        SiteIOCheckoutResp siteIOCheckoutResp = siteIOService.getSiteIOCheckoutRespByRecordId(recordId,mainsiteId);
        return siteIOCheckoutResp;
    }

    @ApiOperation("查询库房列表")
    @GetMapping("/{mainsiteId}/warehouses")
    public List<WarehouseResp> getWarehouseInfoList(@PathVariable("mainsiteId")String mainsiteId){
        List<WarehouseResp> warehouseRespList = warehouseService.getAllWarehouseInfo(mainsiteId);
        return warehouseRespList;
    }

    @ApiOperation("查询库房详细信息")
    @GetMapping("/{mainsiteId}/warehouse/{warehouseId}")
    public WarehouseDetailResp getWarehouseInfoList(@PathVariable("mainsiteId")String mainsiteId,
                                                    @PathVariable("warehouseId")String warehouseId){
        WarehouseDetailResp warehouseDetailResp = warehouseService.getWarehouseDetail(warehouseId,mainsiteId);
        return warehouseDetailResp;
    }
}
