package com.tcsquad.ilogistics.domain.clientele;

import org.joda.time.DateTime;

/**
 * 
 */
public class SupplyIO {
    private long recordId;
    private String supplierId;
    private String mainSiteId;
    private String itemId;
    private int qty;
    private DateTime timeStamp;

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getMainSiteId() {
        return mainSiteId;
    }

    public void setMainSiteId(String mainSiteId) {
        this.mainSiteId = mainSiteId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}