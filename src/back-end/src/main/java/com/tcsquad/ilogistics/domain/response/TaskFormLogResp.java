package com.tcsquad.ilogistics.domain.response;

import java.util.Date;

public class TaskFormLogResp {
    private long taskFormId;
    private String subSiteId;
    private String status;
    private Date shipTime;
    private String sendAddress;
    private String receiveAddress;

    public long getTaskFormId() {
        return taskFormId;
    }

    public void setTaskFormId(long taskFormId) {
        this.taskFormId = taskFormId;
    }

    public String getSubSiteId() {
        return subSiteId;
    }

    public void setSubSiteId(String subSiteId) {
        this.subSiteId = subSiteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }
}
