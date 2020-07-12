package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.storage.Item;

import java.util.Date;

public class SiteIOCheckoutResp {
    private Long recordId;
    private Date timeStamp;
    private String warehouseId;
    private Item item;
    private Integer itemNum;
    private Integer type;           //1：退货给供应商，2：调货出库，3：发货出库
    private String typeDesc;       //对tpe属性的描述，如“调货出库”
    private Long formId;            //相关表单编号
    private String itemDest;        //货物出处，例如：其他主站的编号
    private String approvalStatus;
    private String approver;        //待更改（之后会改为员工编号）

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getItemDest() {
        return itemDest;
    }

    public void setItemDest(String itemDest) {
        this.itemDest = itemDest;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}
