package com.tcsquad.ilogistics.domain.storage;

/**
 * 
 */
public class MainSite {
    private String mainSiteId;
    private String province;
    private String city;
    private String district;

    public String getMainSiteId() {
        return mainSiteId;
    }

    public void setMainSiteId(String mainSiteId) {
        this.mainSiteId = mainSiteId;
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
}