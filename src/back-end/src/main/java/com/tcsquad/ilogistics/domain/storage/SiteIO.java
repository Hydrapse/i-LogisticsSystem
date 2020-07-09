package com.tcsquad.ilogistics.domain.storage;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * 
 */
public class SiteIO {
    private long recordId;
    private DateTimeLiteralExpression.DateTime timeStamp;
    private String siteId;
    private Item item;
    private int qty;
    private boolean isCheckIn;
    private String approver;

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public DateTimeLiteralExpression.DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(DateTimeLiteralExpression.DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}