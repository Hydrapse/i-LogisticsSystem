package com.tcsquad.ilogistics.domain.map;

import org.springframework.data.util.Pair;

public class GeoCodingResult {
    //返回结果状态值， 成功返回0
    //http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding
    private int status;
    private Result result;


    public static class Result{
        //经纬度坐标
        private Location location;

        //位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。
        private int precise;

        //    描述打点绝对精度（即坐标点的误差范围）。
        private int confidence;

        //    描述地址理解程度。分值范围0-100，分值越大，服务对地址理解程度越高（建议以该字段作为解析结果判断标准）；
        //解析误差：地理编码服务解析地址得到的坐标位置，与地址对应的真实位置间的距离。
        private int comprehension;

        //    能精确理解的地址类型，包含：UNKNOWN、国家、省、城市、区县、乡镇、村庄、道路、地产小区、商务大厦、政府机构、交叉路口、商圈、生活服务、休闲娱乐、餐饮、宾馆、购物、金融、教育、医疗 、工业园区 、旅游景点 、汽车服务、火车站、长途汽车站、桥 、停车场/停车区、港口/码头、收费区/收费站、飞机场 、机场 、收费处/收费站 、加油站、绿地、门址
        private String level;

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public int getComprehension() {
            return comprehension;
        }

        public void setComprehension(int comprehension) {
            this.comprehension = comprehension;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "location=" + location +
                    ", precise=" + precise +
                    ", confidence=" + confidence +
                    ", comprehension=" + comprehension +
                    ", level='" + level + '\'' +
                    '}';
        }
    }

    public static class Location{
        //	纬度值
        private double lat;

        //经度值
        private double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GeoCodingResult{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}
