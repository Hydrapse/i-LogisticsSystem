package com.tcsquad.ilogistics.domain.response;

import com.tcsquad.ilogistics.domain.storage.Item;

import java.util.Date;
import java.util.List;

public class SiteIOCheckInResp {
    private Long recordId;
    private Date timeStamp;
    private String warehouseId;     //Todo:应该为可供入库的库房编号列表
    private List<String> warehouseOptionalList;  //warehouseId为warehouseOptionalList[0]
    private Item item;
    private Integer itemNum;
    private Integer type;           //1表示补货，2表示调货，3表示退货，4表示换货
    private String typeDesc;       //对type属性的描述，如“补货”
    private Long formId;            //相关表单编号
    private String itemSrc;        //货物来源，例如：用户名
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

    public String getItemSrc() {
        return itemSrc;
    }

    public void setItemSrc(String itemSrc) {
        this.itemSrc = itemSrc;
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

    public List<String> getWarehouseOptionalList() {
        return warehouseOptionalList;
    }

    public void setWarehouseOptionalList(List<String> warehouseOptionalList) {
        this.warehouseOptionalList = warehouseOptionalList;
    }
}
