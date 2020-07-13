package com.tcsquad.ilogistics.domain.map;

import java.util.List;

public class DistanceResult {
    //    0：成功
//    1：服务器内部错误
//    2：参数错误
    private int status;

    //    对status的中文描述
    private String message;

    //    数组形式。数组中的每个元素代表一个起点和一个终点的检索结果。顺序依次为（以2起点2终点为例）：
//    origin1-destination1,
//    origin1-destination2,
//    origin2-destination1,
//    origin2-destination2
    private List<Result> result;

    public static class Result {
        //        路线距离
//        文本描述的单位有米、公里两种
//        数值的单位为米。若没有计算结果，值为0
        private Message distance;

        //        路线耗时
//        文本描述的单位有分钟、小时两种
//        数值的单位为秒。若没有计算结果，值为0
        private Message duration;

        public Message getDistance() {
            return distance;
        }

        public void setDistance(Message distance) {
            this.distance = distance;
        }

        public Message getDuration() {
            return duration;
        }

        public void setDuration(Message duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "distance=" + distance +
                    ", duration=" + duration +
                    '}';
        }
    }

    public static class Message {
        //        文本描述
        private String text;

        //        数值
        private double value;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "text='" + text + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DistanceResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
