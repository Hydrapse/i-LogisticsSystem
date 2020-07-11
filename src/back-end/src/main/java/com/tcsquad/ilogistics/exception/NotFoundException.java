package com.tcsquad.ilogistics.exception;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description:
 */

public class NotFoundException extends GlobalException
{
    public NotFoundException(String message, int code)
    {
        super(message, code);
    }
}

