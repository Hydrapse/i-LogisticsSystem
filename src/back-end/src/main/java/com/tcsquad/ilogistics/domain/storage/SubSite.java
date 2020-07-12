package com.tcsquad.ilogistics.domain.storage;

import java.math.BigDecimal;

/**
 * 
 */
public class SubSite {
    private String subsiteId;
    private String mainsiteId;
    private String district;
    private String addr;
    private BigDecimal longitude;
    private BigDecimal latitude;


    public String getSubsiteId() {
        return subsiteId;
    }

    public void setSubsiteId(String subsiteId) {
        this.subsiteId = subsiteId;
    }

    public String getMainsiteId() {
        return mainsiteId;
    }

    public void setMainsiteId(String mainsiteId) {
        this.mainsiteId = mainsiteId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


}