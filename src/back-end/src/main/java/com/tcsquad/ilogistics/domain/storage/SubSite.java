package com.tcsquad.ilogistics.domain.storage;

/**
 * 
 */
public class SubSite {
    private String subSiteId;
    private String mainSiteId;
    private String district;

    public String getSubSiteId() {
        return subSiteId;
    }

    public void setSubSiteId(String subSiteId) {
        this.subSiteId = subSiteId;
    }

    public String getMainSiteId() {
        return mainSiteId;
    }

    public void setMainSiteId(String mainSiteId) {
        this.mainSiteId = mainSiteId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}