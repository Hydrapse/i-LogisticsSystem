package com.tcsquad.ilogistics.domain.request;

/**
 * @Author intmian
 * @Description
 * @Created 16:09 2020/7/11
 **/
public class TaskFormReq {
    private String[] q; //关键词

    public String[] getQ() {
        return q;
    }

    public void setQ(String q) {
        if (q == null || q.isBlank()) {
            this.q = null;
        } else {
            this.q = q.split("[ ]+");//任意长空串
        }
    }

    public static void main(String[] args) {
        var tmp = new TaskFormReq();
        tmp.setQ("1 2  3 ");
        for (String s : tmp.getQ())
            System.out.println("|"+s+"|");
        System.out.println(tmp.getQ().length);
    }
}
