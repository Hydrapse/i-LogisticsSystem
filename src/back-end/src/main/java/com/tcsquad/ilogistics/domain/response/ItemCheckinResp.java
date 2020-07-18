package com.tcsquad.ilogistics.domain.response;

public class ItemCheckinResp {
    Integer type;           //1表示补货，2表示调货，3表示退货，4表示换货
    String typeDesc;       //对type属性的描述
    Long formId;            //例如：调货的调货单编号
    String itemId;
    Integer itemNum;
    Long recordId;
    String mainsiteId;

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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getMainsiteId() {
        return mainsiteId;
    }

    public void setMainsiteId(String mainsiteId) {
        this.mainsiteId = mainsiteId;
    }
}
