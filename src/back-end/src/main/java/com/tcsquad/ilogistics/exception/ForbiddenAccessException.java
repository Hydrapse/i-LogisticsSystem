package com.tcsquad.ilogistics.exception;

import com.tcsquad.ilogistics.domain.ErrorCode;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description: 权限不足异常
 */

public class ForbiddenAccessException extends GlobalException {

    public ForbiddenAccessException(String message) {
        super(message);
        setCode(ErrorCode.USER_FORBIDDEN_ACCESS.getCode());
    }

    public ForbiddenAccessException(String message, int code) {
        super(message, code);
    }
}
