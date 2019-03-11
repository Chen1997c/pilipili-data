package com.pilipili.common.util;

import lombok.NoArgsConstructor;

/**
 * 描述： 业务异常
 *
 * @author ChenJianChuan
 * @date 2019/3/5　11:09
 */
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
