package com.tcsquad.ilogistics.domain.storage;

/**
 * 
 */
public class SubSite {
    private String subsiteId;
    private String mainsiteId;
    private String district;

    public String getsubsiteId() {
        return subsiteId;
    }

    public void setsubsiteId(String subsiteId) {
        this.subsiteId = subsiteId;
    }

    public String getmainsiteId() {
        return mainsiteId;
    }

    public void setmainsiteId(String mainsiteId) {
        this.mainsiteId = mainsiteId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}