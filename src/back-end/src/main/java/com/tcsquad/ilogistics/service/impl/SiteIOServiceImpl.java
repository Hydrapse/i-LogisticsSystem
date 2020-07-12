package com.tcsquad.ilogistics.service.impl;

import com.tcsquad.ilogistics.domain.StatusString;
import com.tcsquad.ilogistics.domain.request.SiteIOAddReq;
import com.tcsquad.ilogistics.domain.storage.SiteIO;
import com.tcsquad.ilogistics.mapper.storage.SiteIOMapper;
import com.tcsquad.ilogistics.service.SiteIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SiteIOServiceImpl implements SiteIOService {
    @Autowired
    SiteIOMapper siteIOMapper;

    @Override
    public void cancelSiteIOStatus(String recordId) {
        siteIOMapper.updateSiteIOStatus(recordId, StatusString.INVALID.getValue());
    }

    @Override
    public void confirmSiteIORecord(String recordId) {
        siteIOMapper.updateSiteIOStatus(recordId,StatusString.CONFIRM.getValue());
    }

    //1表示补货，2表示调货，3表示退货，4表示换货
    @Override
    public void insertCheckinRecord(SiteIOAddReq siteIOAddReq) {
        SiteIO siteIO = new SiteIO();
        String typeValue = "";
        switch (siteIOAddReq.getType()){
            case 1:
                typeValue = StatusString.SUPPLY_IN.getValue();break;
            case 2:
                typeValue = StatusString.ADJUST_IN.getValue();break;
            case 3:
                typeValue = StatusString.RETURN_IN.getValue();break;
            case 4:
                typeValue = StatusString.RETURN_IN.getValue();
            default:
                System.out.println("type值不合法");
        }

        if(typeValue != ""){
            Date currentDate = new Date();
            siteIO.setTimeStamp(currentDate);
            siteIO.setWarehouseId(siteIOAddReq.getWarehouseId());
            siteIO.setItemId(siteIOAddReq.getItemId());
            siteIO.setQty(siteIOAddReq.getItemNum());
            siteIO.setType(typeValue);
            siteIO.setFormId(siteIOAddReq.getFormId());
            siteIO.setApprovalStatus(StatusString.WAITING.getValue());
            siteIO.setApprover("Auto");                //"Auto"表示为程序根据请求自动出入库
            siteIOMapper.insertSiteIORecord(siteIO);
        }
        else {
            //出错
        }

    }

    //1：退货给供应商，2：调货出库，3：发货出库
    @Override
    public void insertCheckOutRecord(SiteIO siteIO) {
        siteIO.setApprovalStatus(StatusString.WAITING.getValue());
        siteIOMapper.insertSiteIORecord(siteIO);
    }
}
