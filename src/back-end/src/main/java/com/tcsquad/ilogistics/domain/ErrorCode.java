package com.tcsquad.ilogistics.domain;

/**
 * 错误代码枚举类
 */
public enum ErrorCode {
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(40401),

    /**
     * 订单已经提交
     */
    ORDER_ALREADY_SUBMIT(40001),

    /**
     *用户权限不足, 无法调用接口
     */
    USER_FORBIDDEN_ACCESS(40301),

    /**
     *消息队列服务器宕机
     */
    MESSAGE_QUEUE_DOWNTIME(50001),
    ;

    private int code;

    public int getCode()
    {
        return code;
    }

    ErrorCode(int code)
    {
        this.code = code;
    }
}
