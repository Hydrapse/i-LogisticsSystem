package com.tcsquad.ilogistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.domain.ErrorCode;
import com.tcsquad.ilogistics.domain.request.OrderSettingsReq;
import com.tcsquad.ilogistics.domain.request.SiteIOSettingsReq;
import com.tcsquad.ilogistics.exception.NotFoundException;
import com.tcsquad.ilogistics.settings.OrderSetting;
import com.tcsquad.ilogistics.settings.SiteIOSetting;
import com.tcsquad.ilogistics.settings.SiteOutSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api(tags = "配置策略模块API接口")
@RestController
@RequestMapping("/settings")
public class SettingsController {
    private static Logger logger = LoggerFactory.getLogger(SettingsController.class);

    @Autowired
    OrderSetting orderSetting;
    @Autowired
    SiteIOSetting siteIOSetting;
    @Autowired
    SiteOutSetting siteOutSetting;

    @ApiOperation("获取出入库审核策略")
    @GetMapping("/siteIOSettings")
    public Object getSiteIOSetting(){
        logger.info("获取出入库审核策略");
        JSONObject settingsObj = new JSONObject();
        settingsObj.put("enable",siteIOSetting.isAvailable());
        settingsObj.put("totalPriceLimit",siteIOSetting.isTotalPriceLimit());
        settingsObj.put("totalPriceAmount",siteIOSetting.getTotalPriceAmount());
        settingsObj.put("totalNumLimit",siteIOSetting.isTotalNumLimit());
        settingsObj.put("totalNum",siteIOSetting.getTotalNum());
        settingsObj.put("categoryIdLimit",siteIOSetting.isCategoryIdLimit());
        settingsObj.put("categoryIdWhiteList",siteIOSetting.getCategoryIdWhiteList());
        settingsObj.put("siteInTypeLimit",siteIOSetting.isSiteInTypeLimit());
        settingsObj.put("siteInTypeWhiteList",siteIOSetting.getSiteInTypeWhiteList());
        settingsObj.put("siteOutTypeLimit",siteIOSetting.isSiteOutTypeLimit());
        settingsObj.put("siteOutTypeWhiteList",siteIOSetting.getSiteOutTypeWhiteList());
        return settingsObj;
    }

    @ApiOperation("修改出入库审核策略")
    @PutMapping("/siteIOSettings")
    public void updateSiteIOSetting(SiteIOSettingsReq siteIOSettingsReq){
        if(siteIOSettingsReq == null){
            throw new NotFoundException("修改参数为空，修改失败",
                    ErrorCode.MISS_PARAMS.getCode());
        }
        if(siteIOSettingsReq.getEnable()){
            siteIOSetting.enableSetting();
            logger.info("修改出入库审核策略");

            //入库单总金额 判断策略
            if(siteIOSettingsReq.getTotalPriceLimit() != null){
                siteIOSetting.setTotalPriceLimit(siteIOSettingsReq.getTotalPriceLimit());
                if(siteIOSettingsReq.getTotalPriceLimit() && siteIOSettingsReq.getTotalPriceAmount()!=null
                        && (siteIOSettingsReq.getTotalPriceAmount().compareTo(new BigDecimal(0)) >= 0)){
                    logger.info("修改入库单总金额判断阈值为：" + siteIOSettingsReq.getTotalPriceAmount());
                    siteIOSetting.setTotalPriceAmount(siteIOSettingsReq.getTotalPriceAmount());
                }
            }

            //入库数量 判断策略
            if(siteIOSettingsReq.getTotalNumLimit() != null){
                siteIOSetting.setTotalNumLimit(siteIOSettingsReq.getTotalNumLimit());
                if(siteIOSettingsReq.getTotalNumLimit() && siteIOSettingsReq.getTotalNum()!=null
                        &&siteIOSettingsReq.getTotalNum() >= 0){
                    logger.info("修改入库数量判断阈值为：" + siteIOSettingsReq.getTotalNum());
                    siteIOSetting.setTotalNum(siteIOSettingsReq.getTotalNum());
                }
            }

            //大类审核白名单
            if(siteIOSettingsReq.getCategoryIdLimit() != null){
                siteIOSetting.setCategoryIdLimit(siteIOSettingsReq.getCategoryIdLimit());
                if(siteIOSettingsReq.getCategoryIdLimit() && siteIOSettingsReq.getCategoryIdWhiteList()!=null){
                    logger.info("修改大类审核白名单为："+ siteIOSettingsReq.getCategoryIdWhiteList().toString());
                    siteIOSetting.setCategoryIdWhiteList(siteIOSettingsReq.getCategoryIdWhiteList());
                }
            }

            //入库类型审核白名单
            if(siteIOSettingsReq.getSiteInTypeLimit() != null){
                siteIOSetting.setSiteInTypeLimit(siteIOSettingsReq.getSiteInTypeLimit());
                if(siteIOSettingsReq.getSiteInTypeLimit() && siteIOSettingsReq.getSiteInTypeWhiteList()!=null){
                    logger.info("修改入库类型审核白名单为："+ siteIOSettingsReq.getSiteInTypeWhiteList().toString());
                    siteIOSetting.setSiteInTypeWhiteList(siteIOSettingsReq.getSiteInTypeWhiteList());
                }
            }

            //出库类型审核白名单
            if(siteIOSettingsReq.getSiteOutTypeLimit() != null){
                siteIOSetting.setSiteOutTypeLimit(siteIOSettingsReq.getSiteOutTypeLimit());
                if(siteIOSettingsReq.getSiteOutTypeLimit() && siteIOSettingsReq.getSiteOutTypeWhiteList() != null){
                    logger.info("修改出库类型审核白名单为："+ siteIOSettingsReq.getSiteOutTypeWhiteList().toString());
                    siteIOSetting.setSiteOutTypeWhiteList(siteIOSettingsReq.getSiteOutTypeWhiteList());
                }
            }

        }
        else {
            siteIOSetting.disableSetting();
            logger.info("关闭出入库审核策略");
        }
    }


    @ApiOperation("获取订单审核策略")
    @GetMapping("/orderSettings")
    public Object getOrderSetting(){
        logger.info("获取订单审核策略");
        JSONObject settingsObj = new JSONObject();
        settingsObj.put("enable",orderSetting.isAvailable());
        settingsObj.put("totalPriceLimit",orderSetting.isTotalPriceLimit());
        settingsObj.put("totalPriceAmount",orderSetting.getTotalPriceAmount());
        settingsObj.put("categoryIdLimit",orderSetting.isCategoryIdLimit());
        settingsObj.put("categoryIdWhiteList",orderSetting.getCategoryIdWhiteList());
        settingsObj.put("customerIdLimit",orderSetting.isCustomerIdLimit());
        settingsObj.put("customerIdWhiteList",orderSetting.getCustomerIdWhiteList());
        return settingsObj;
    }

    @ApiOperation("修改订单审核策略")
    @PutMapping("/orderSettings")
    public void updateOrderSetting(OrderSettingsReq orderSettingsReq){
        if(orderSettingsReq == null){
            throw new NotFoundException("修改参数为空，修改失败",
                    ErrorCode.MISS_PARAMS.getCode());
        }
        if(orderSettingsReq.getEnable()){
            orderSetting.enableSetting();
            logger.info("修改订单审核策略");

            //订单总金额 判断策略
            if(orderSettingsReq.getTotalPriceLimit() != null){
                orderSetting.setTotalPriceLimit(orderSettingsReq.getTotalPriceLimit());
                if(orderSettingsReq.getTotalPriceLimit() && orderSettingsReq.getTotalPriceAmount()!=null
                        && (orderSettingsReq.getTotalPriceAmount().compareTo(new BigDecimal(0)) >= 0)){
                    logger.info("修改订单总金额判断阈值为：" + orderSettingsReq.getTotalPriceAmount());
                    orderSetting.setTotalPriceAmount(orderSettingsReq.getTotalPriceAmount());
                }
            }

            //大类审核白名单
            if(orderSettingsReq.getCategoryIdLimit() != null){
                orderSetting.setCategoryIdLimit(orderSettingsReq.getCategoryIdLimit());
                if(orderSettingsReq.getCategoryIdLimit() && orderSettingsReq.getCategoryIdWhiteList()!=null){
                    logger.info("修改大类审核白名单为："+ orderSettingsReq.getCategoryIdWhiteList().toString());
                    orderSetting.setCategoryIdWhiteList(orderSettingsReq.getCategoryIdWhiteList());
                }
            }

            //顾客白名单
            if(orderSettingsReq.getCustomerIdLimit() != null){
                orderSetting.setCategoryIdLimit(orderSettingsReq.getCustomerIdLimit());
                if(orderSettingsReq.getCustomerIdLimit() && orderSettingsReq.getCustomerIdWhiteList() != null){
                    logger.info("修改顾客白名单为："+ orderSettingsReq.getCustomerIdWhiteList().toString());
                    orderSetting.setCustomerIdWhiteList(orderSettingsReq.getCustomerIdWhiteList());
                }
            }

        }
        else {
            orderSetting.disableSetting();
            logger.info("关闭订单审核策略");
        }
    }

    @ApiOperation("获取出库分拣策略")
    @GetMapping("/siteoutSettings")
    public Object getSiteOutSetting(){
        logger.info("获取出库分拣策略");
        JSONObject obj = new JSONObject();
        obj.put("option",siteOutSetting.getOption());
        obj.put("threshold",siteOutSetting.getThreshold());
        return obj;
    }

    @ApiOperation("修改出库分拣策略")
    @PutMapping("/siteoutSettings")
    public void updateSiteOutSetting(Integer option,Integer threshold){
        if(option !=null){
            logger.info("修改出库分拣策略");
            if(option == 1){
                logger.info("出库分拣策略修改为选项一");
                siteOutSetting.selectOption1();
            }
            else if(option == 2){
                logger.info("出库分拣策略修改为选项二");
                siteOutSetting.selectOption2();
            }
            else if(option == 3 && threshold != null && threshold >=0){
                logger.info("出库分拣策略修改为选项三");
                siteOutSetting.selectOption3();
                siteOutSetting.setThreshold(threshold);
            }
            else {
                throw new NotFoundException("修改参数错误，修改失败",
                        ErrorCode.MISS_PARAMS.getCode());
            }
        }
        else {
            throw new NotFoundException("修改参数为空，修改失败",
                    ErrorCode.MISS_PARAMS.getCode());
        }
    }
}
