package com.tcsquad.ilogistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.tcsquad.ilogistics.settings.OrderSetting;
import com.tcsquad.ilogistics.settings.SiteIOSetting;
import com.tcsquad.ilogistics.settings.SiteOutSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "配置策略模块API接口")
@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    OrderSetting orderSetting;

    @Autowired
    SiteIOSetting siteIOSetting;

    @Autowired
    SiteOutSetting siteOutSetting;

    @ApiOperation("获取出入库审核策略")
    @GetMapping("/siteIOSettings")
    public Object getSiteIOSetting(){
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



}
