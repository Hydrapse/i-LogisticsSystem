package com.tcsquad.ilogistics.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class RouteResp {
    private double distance;
    private double duration;
    private List<Step> steps;

    @Data
    public static class Step{
        private int leg_index;
        private Point start_location;
        private Point end_location;
    }

    @Data
    @AllArgsConstructor
    public static class Point{
        private double lng;
        private double lat;
    }
}
