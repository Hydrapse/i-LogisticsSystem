package com.tcsquad.ilogistics.exception;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description: 后端服务器异常
 */

public class InternalServerErrorException  extends GlobalException{

    public InternalServerErrorException(String message, int code) {
        super(message, code);
    }
}
