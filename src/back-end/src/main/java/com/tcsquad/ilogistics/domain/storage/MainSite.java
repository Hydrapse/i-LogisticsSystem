package com.tcsquad.ilogistics.domain.storage;

import java.math.BigDecimal;

/**
 * 
 */
public class MainSite {
    private String mainsiteId;
    private String province;
    private String city;
    private String district;
    private String addr;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public String getMainsiteId() {
        return mainsiteId;
    }

    public void setMainsiteId(String mainsiteId) {
        this.mainsiteId = mainsiteId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
}