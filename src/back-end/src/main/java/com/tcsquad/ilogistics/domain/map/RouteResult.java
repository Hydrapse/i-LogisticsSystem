package com.tcsquad.ilogistics.domain.map;

import lombok.Data;

import java.util.List;

/**
 * 比较复杂，具体解释看文档: http://lbsyun.baidu.com/index.php?title=webapi/direction-api-v2#service-page-anchor-1-3
 */
@Data
public class RouteResult {
    private int status;
    private String message;
    private int type;
    private Result result;

    @Data
    public static class Result{
        private String restriction;
        private String session_id; //复杂类型，文档说不必关注
        private int total;
        private List<Route> routes;
    }

//    @Data
//    public static class SessionId{
//        private String codr;
//        private String loc;
//    }

    @Data
    public static class Route{
        private Point origin;
        private Point destination;
        private String tag;
        private String route_id; //复杂类型，文档说不必关注
        private double distance;
        private int duration;
//        private int ext_duration;
//        private int suggest_departure_time;
        private double taxi_fee;
        private Object restriction_info; //复杂类型（限行信息），文档中没有
        private double toll;
        private double toll_distance;
        private List<Step> steps;
    }

    @Data
    public static class Step {
        private int leg_index;
        private int direction;
        private double distance;
        private String road_name;
        private int road_type;
        private double toll;
        private double toll_distance;
        private String toll_gate_name;
        private Point toll_gate_location;
        private Point start_location;
        private Point end_location;
        private String path;
        private String adcodes;
        private List<Traffic> traffic_condition;

        private int duration; //文档中没有
    }

    @Data
    public static class Traffic{
        private int status;
        private int geo_cnt;
        private double distance;
    }

    @Data
    public static class Point{
        private double lng;
        private double lat;
    }
}
