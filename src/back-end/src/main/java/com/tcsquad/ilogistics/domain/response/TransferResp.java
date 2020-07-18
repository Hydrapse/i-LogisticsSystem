package com.tcsquad.ilogistics.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TransferResp {
    private long adjustId;
    private String itemId;
    private int itemNum;
    private String from;
    private String to;
    private String status;
    private Point fromPoint;
    private Point toPoint;

    @AllArgsConstructor
    @Data
    public static class Point {
        private double lat;
        private double lng;
    }
}
