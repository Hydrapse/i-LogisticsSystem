package com.tcsquad.ilogistics.domain.order;

import java.util.Date;

public class Order {
    private Long orderId;
    private String customerId;
    private String payStatus;
    private String processStatus;
    private Date createDateTime;
    private Date payDateTime;
    private Date deliveryDateTime;
    private String billName;
    private String billPro;
    private String billCity;
    private String billDistrict;
    private String billAddr;
    private double totalPrice;
    private double shippingCost;
    private String payMethod = "支付宝";
    private String note;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
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

    public String getBillDistrict() {
        return billDistrict;
    }

    public void setBillDistrict(String billDistrict) {
        this.billDistrict = billDistrict;
    }

    public String getBillAddr() {
        return billAddr;
    }

    public void setBillAddr(String billAddr) {
        this.billAddr = billAddr;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getPayDateTime() {
        return payDateTime;
    }

    public void setPayDateTime(Date payDateTime) {
        this.payDateTime = payDateTime;
    }

    public Date getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(Date deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }
}
