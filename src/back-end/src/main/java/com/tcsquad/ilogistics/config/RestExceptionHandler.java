package com.tcsquad.ilogistics.config;

import com.tcsquad.ilogistics.domain.Result;
import com.tcsquad.ilogistics.exception.BusinessErrorException;
import com.tcsquad.ilogistics.exception.InternalServerErrorException;
import com.tcsquad.ilogistics.exception.NotFoundException;
import com.tcsquad.ilogistics.exception.ForbiddenAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Hydra
 * @date 2020/7/10
 * @description: 处理REST请求自定义异常
 */

@ControllerAdvice
public class RestExceptionHandler
{
    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleResourceNotFoundException(NotFoundException e)
    {
        logger.warn(e.getMessage(), e);
        return new Result(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(value = BusinessErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBusinessErrorException(BusinessErrorException e)
    {
        logger.warn(e.getMessage(), e);
        return new Result(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(value = InternalServerErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleInternalServerErrorException(InternalServerErrorException e)
    {
        logger.error(e.getMessage(), e);
        return new Result(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(value = ForbiddenAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result handleForbiddenAccessException(ForbiddenAccessException e)
    {
        logger.warn(e.getMessage(), e);
        return new Result(e.getMessage(), e.getCode());
    }
}
