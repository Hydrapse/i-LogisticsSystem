package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.order.OrderItem;

import java.util.Date;
import java.util.List;

public class TaskFormLogResp {
    private long orderId;
    private long taskFormId;
    private String subSiteId;
    private String status;
    private Date shipTime;
    private String receiverName;
    private String receiverTel;
    private String receiverAddress;
    private List<OrderItem> orderItems;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
