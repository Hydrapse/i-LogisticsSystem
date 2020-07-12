package com.tcsquad.ilogistics.domain.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskForm {

    private int taskId;
    private int orderId;
    private String subsiteId;
    private String courier;
    private String status;
    private Date deliveryDateTime;
    private String shipName;
    private String shipTel;
    private String shipPro;
    private String shipCity;
    private String shipDis;
    private String shipAddr;
    private String billName;
    private String billTel;
    private String billPro;
    private String billCity;
    private String billDis;
    private String billAddr;
    private BigDecimal totalPrice;//任务单付款信息
    private String note;//备注
    private List<OrderItem> orderItems = new ArrayList<>();

    public Date getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(Date deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr;
    }

    public String getBillAddr() {
        return billAddr;
    }

    public void setBillAddr(String billAddr) {
        this.billAddr = billAddr;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSubsiteId() {
        return subsiteId;
    }

    public void setSubsiteId(String subsiteId) {
        this.subsiteId = subsiteId;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipTel() {
        return shipTel;
    }

    public void setShipTel(String shipTel) {
        this.shipTel = shipTel;
    }

    public String getShipPro() {
        return shipPro;
    }

    public void setShipPro(String shipPro) {
        this.shipPro = shipPro;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipDis() {
        return shipDis;
    }

    public void setShipDis(String shipDis) {
        this.shipDis = shipDis;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillTel() {
        return billTel;
    }

    public void setBillTel(String billTel) {
        this.billTel = billTel;
    }

    public String getBillPro() {
        return billPro;
    }

    public void setBillPro(String billPro) {
        this.billPro = billPro;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillDis() {
        return billDis;
    }

    public void setBillDis(String billDis) {
        this.billDis = billDis;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
