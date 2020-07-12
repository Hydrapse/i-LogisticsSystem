package com.tcsquad.ilogistics.domain.request;

/**
 * @Author intmian
 * @Description
 * @Created 16:09 2020/7/11
 **/
public class TaskFormReq {
    private String[] q; //关键词
    private Long taskFormId; //任务单id
    private Integer pageNum;
    private Integer pageSize;

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

    public Long getTaskFormId() {
        return taskFormId;
    }

    public void setTaskFormId(Long taskFormId) {
        this.taskFormId = taskFormId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static void main(String[] args) {
        var tmp = new TaskFormReq();
        tmp.setQ("1 2  3 ");
        for (String s : tmp.getQ())
            System.out.println("|"+s+"|");
        System.out.println(tmp.getQ().length);
    }
}
