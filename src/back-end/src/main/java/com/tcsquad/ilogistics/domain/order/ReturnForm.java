package com.tcsquad.ilogistics.domain.order;

import com.tcsquad.ilogistics.domain.storage.Item;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * 
 */
public class ReturnForm {
    private long formId;
    private long orderId;
    private int isChange;
    private Item item;
    private int itemNum;
    private BigDecimal returnMoney;
    private DateTime applyTime;
    private DateTime processTime;
    private String processStatus;
    private String reason;

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getIsChange() {
        return isChange;
    }

    public void setIsChange(int isChange) {
        this.isChange = isChange;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public DateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(DateTime applyTime) {
        this.applyTime = applyTime;
    }

    public DateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(DateTime processTime) {
        this.processTime = processTime;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }
}