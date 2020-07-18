package com.tcsquad.ilogistics.controller;

import com.tcsquad.ilogistics.domain.PageResult;
import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.request.InventoryUpdateReq;
import com.tcsquad.ilogistics.domain.request.ItemInventoryGetReq;
import com.tcsquad.ilogistics.domain.request.PageRequest;
import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.response.*;
import com.tcsquad.ilogistics.service.interf.ItemService;
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
    @Autowired
    ItemService itemService;

    @ApiOperation("入库请求")
    @PostMapping("/{mainsiteId}/inventory/inquery")
    public void insertCheckinRecord(@PathVariable("mainsiteId")String mainsiteId,
                                    @RequestBody SiteIOAddReq siteIOAddReq){
        List<String> warehouseList = warehouseService.getWarehouseOptionsToCheckin(siteIOAddReq.getItemId(),siteIOAddReq.getItemNum(),mainsiteId);
        if(warehouseList.isEmpty()){
            //Todo: 根据itemId和mainsiteId未能找到相匹配的单个库房，需要将入库商品存放在多个库房中

        }

        //这里将该商品存放在第一个仓库
        //Todo:之后应该要加上一些策略配置
        siteIOAddReq.setWarehouseId(warehouseList.get(0));
        Long newRecordId = siteIOService.insertCheckinRecord(siteIOAddReq);
        ItemCheckinResp itemCheckinResp = siteIOService.getItemCheckinRespByRecordId(newRecordId);

        //mailUtil.sendMail("1041422509@qq.com","生成入库请求","入库请求");

        if(siteIOService.isCheckNeeded(siteIOAddReq)){
            //需要审核则发送出库消息
            siteIOService.sendItemCheckinMessage(itemCheckinResp);
            return;
        }

        //不需要审核则直接确认入库
        siteIOService.confirmSiteIORecord(newRecordId,true);

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
        if(!warehouseId.equals("") && warehouseId!=null){
            siteIOService.updateWarehouseToCheckin(recordId,warehouseId);
        }

        if(approvalStatus.equals(StatusString.CONFIRM.getValue())){ //确认审核
            siteIOService.confirmSiteIORecord(recordId,true);
        }
        else if(approvalStatus.equals(StatusString.INVALID.getValue())) { //取消审核
            siteIOService.cancelSiteIOStatus(recordId,true);
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

    @ApiOperation("修改出库单信息")
    @PatchMapping("/{mainsiteId}/inventory/siteout/{recordId}")
    public String updateSiteIOCheckoutInfo(@PathVariable("mainsiteId")String mainsiteId,
                                          @PathVariable("recordId")Long recordId,
                                          String warehouseId,String approvalStatus){
        /*
        1. 修改出库库房信息
        2. 修改出库状态，其中包含对其他模块的调用（例如调货单状态的修改）
         */
        if(!warehouseId.equals("") && warehouseId!=null){
            siteIOService.updateWarehouseToCheckout(recordId,warehouseId);
        }

        if(approvalStatus.equals(StatusString.CONFIRM.getValue())){ //确认审核
            siteIOService.confirmSiteIORecord(recordId,false);
        }
        else if(approvalStatus.equals(StatusString.INVALID.getValue())) { //取消审核
            siteIOService.cancelSiteIOStatus(recordId,false);
        }
        else{
            //Todo:Error
        }
        return "Success";
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

    @ApiOperation("根据限定条件获取库房货物列表")
    @GetMapping("/{mainsiteId}/items")
    public PageResult productList(@PathVariable("mainsiteId")String mainsiteId,ItemInventoryGetReq req, PageRequest pageRequest){
        //初始校验pageRequest
        pageRequest.initialValidate(1, 6);
        req.setMainsiteId(mainsiteId);

//        logger.info("查询请求: " + prodKey);
//        logger.info("分页数据: " + pageRequest);

        PageResult pageResult = itemService.getItemInventoryRespByRequest(req, pageRequest);

        return pageResult;
    }

    @ApiOperation("更新库房具体商品库存")
    @PatchMapping("/{mainsiteId}/items/{itemId}")
    public void updateItemInventoryBetweenWarehouses(@PathVariable("mainsiteId")String mainsiteId,
                                                     @PathVariable("itemId")String itemId,
                                                     InventoryUpdateReq inventoryUpdateReq){
        inventoryUpdateReq.setMainsiteId(mainsiteId);
        inventoryUpdateReq.setItemId(itemId);
        warehouseService.updateItemInventoryBetweenWarehouses(inventoryUpdateReq);

    }

}
