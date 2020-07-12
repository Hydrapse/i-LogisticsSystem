package com.tcsquad.ilogistics.domain.storage;

/**
 * 
 */
public class AdjustForm {
    private long adjustId;           //调货单编号
    private Item item;               //调货商品
    private int itemNum;             //调货数量
    private String toMainSiteId;     //处理调货主站编号
    private String fromMainSiteId;   //请求调货主站编号
    private String adjustStatus;     //调货单处理状态


    public long getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(long adjustId) {
        this.adjustId = adjustId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public String getToMainSiteId() {
        return toMainSiteId;
    }

    public void setToMainSiteId(String toMainSiteId) {
        this.toMainSiteId = toMainSiteId;
    }

    public String getFromMainSiteId() {
        return fromMainSiteId;
    }

    public void setFromMainSiteId(String fromMainSiteId) {
        this.fromMainSiteId = fromMainSiteId;
    }

    public String getAdjustStatus() {
        return adjustStatus;
    }

    public void setAdjustStatus(String adjustStatus) {
        this.adjustStatus = adjustStatus;
    }
}