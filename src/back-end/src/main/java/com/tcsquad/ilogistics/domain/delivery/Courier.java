package com.tcsquad.ilogistics.domain.delivery;

import java.math.BigDecimal;

/**
 * 
 */
public class Courier {
    private long courierId;
    private String name;
    private String subSiteId;
    private String tel;
    private BigDecimal commentLevel;

    public long getCourierId() {
        return courierId;
    }

    public void setCourierId(long courierId) {
        this.courierId = courierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubSiteId() {
        return subSiteId;
    }

    public void setSubSiteId(String subSiteId) {
        this.subSiteId = subSiteId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public BigDecimal getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(BigDecimal commentLevel) {
        this.commentLevel = commentLevel;
    }
}