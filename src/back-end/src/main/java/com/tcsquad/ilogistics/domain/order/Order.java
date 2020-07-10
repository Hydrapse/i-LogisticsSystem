package com.tcsquad.ilogistics.domain.order;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

public class Order {
    private long orderId;
    private String customerId;
    private String payStatus;
    private String processStatus;
    private DateTimeLiteralExpression.DateTime createDateTime;
    private DateTimeLiteralExpression.DateTime payDateTime;
    private DateTimeLiteralExpression.DateTime deliveryDateTime;//订单发货时间
    private String billName;
    private String billPro;
    private String billCity;
    private String billDistrict;
    private String billAddr;
    private double totalPrice;
    private double shippingCost;//运费
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

    public DateTimeLiteralExpression.DateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(DateTimeLiteralExpression.DateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public DateTimeLiteralExpression.DateTime getPayDateTime() {
        return payDateTime;
    }

    public void setPayDateTime(DateTimeLiteralExpression.DateTime payDateTime) {
        this.payDateTime = payDateTime;
    }

    public DateTimeLiteralExpression.DateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(DateTimeLiteralExpression.DateTime deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
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
}
