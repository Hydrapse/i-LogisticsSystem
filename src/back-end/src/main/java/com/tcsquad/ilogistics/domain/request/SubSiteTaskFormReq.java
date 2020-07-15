package com.tcsquad.ilogistics.domain.request;

public class SubSiteTaskFormReq {
    private String[] q;
    private String status;

    public String[] getQ() {
        return q;
    }

    public void setQ(String q) {
        if(q == null || q.isBlank())
            this.q = null;
        else
            this.q = q.split("[ ]+");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
