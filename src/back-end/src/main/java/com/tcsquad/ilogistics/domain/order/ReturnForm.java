package com.tcsquad.ilogistics.domain.order;

import com.tcsquad.ilogistics.domain.storage.Item;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.math.BigDecimal;

/**
 * 
 */
public class ReturnForm {
    private int formId;
    private int orderId;
    private int isChange;
    private Item item;
    private int itemNum;
    private BigDecimal returnMoney;
    private DateTimeLiteralExpression.DateTime applyTime;
    private DateTimeLiteralExpression.DateTime processTime;
    private String processStatus;
    private String reason;

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    public DateTimeLiteralExpression.DateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(DateTimeLiteralExpression.DateTime applyTime) {
        this.applyTime = applyTime;
    }

    public DateTimeLiteralExpression.DateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(DateTimeLiteralExpression.DateTime processTime) {
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