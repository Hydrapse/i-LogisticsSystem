package com.tcsquad.ilogistics.exception;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description: 业务错误异常
 */
public class BusinessErrorException extends GlobalException{

    public BusinessErrorException(String message, int code) {
        super(message, code);
    }
}
