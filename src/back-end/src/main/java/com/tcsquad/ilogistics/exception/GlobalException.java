package com.tcsquad.ilogistics.exception;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description:
 */

public class GlobalException extends RuntimeException {

    private int code;

    public GlobalException(String message)
    {
        super(message);
    }

    public GlobalException(String message, int code)
    {
        super(message);
        this.code = code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
}