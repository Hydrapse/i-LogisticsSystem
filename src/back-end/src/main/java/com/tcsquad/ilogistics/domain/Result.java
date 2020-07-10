package com.tcsquad.ilogistics.domain;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description: 错误响应
 */
public class Result {

    /**
     * 错误内容
     */
    private String msg;

    /**
     * 自定义错误码
     */
    private int code;

    public Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
