package com.pilipili.common.exception;

/**
 * 描述： 数据解析异常
 *
 * @author ChenJianChuan
 * @date 2019/3/28　15:35
 */
public class ResolveDataException extends RuntimeException {

    public ResolveDataException(String message) {
        super(message);
    }

    public ResolveDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
