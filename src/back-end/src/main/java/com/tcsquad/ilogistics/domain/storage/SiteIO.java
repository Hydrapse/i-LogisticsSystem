package com.tcsquad.ilogistics.domain.storage;

import java.util.Date;

/**
 *
 */
public class SiteIO {
    /**
     * type值说明
     * 补货入库（IN-01）/调货入库（IN-02）/退货入库（IN-03）；
     * 退货给供应商（OUT-01）/调货出库（OUT-02）/发货出库（OUT-03）
     */
    public static String SUPPLY_IN  = "IN-01";
    public static String ADJUST_IN  = "IN-02";
    public static String RETURN_IN  = "IN-03";
    public static String SUPPLY_OUT = "OUT-01";
    public static String ADJUST_OUT = "OUT-02";
    public static String SHIP_OUT   = "OUT-03";

    private long recordId;
    private Date timeStamp;
    private String siteId;
    private Item item;
    private int qty;
    //private boolean isCheckIn;
    private String type;        //出入库类型
    private String approver;

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}