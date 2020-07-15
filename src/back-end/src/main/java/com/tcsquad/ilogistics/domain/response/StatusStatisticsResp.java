package com.tcsquad.ilogistics.domain.response;

public class StatusStatisticsResp {
    private String status;
    private int number;

    public StatusStatisticsResp() {
    }

    public StatusStatisticsResp(String status, int number) {
        this.status = status;
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
