package com.pilipili.provider.web;

import com.pilipili.common.util.BusinessException;
import com.pilipili.common.util.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 描述： 全局异常处理~
 *
 * @author ChenJianChuan
 * @date 2019/3/6　10:06
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResultWrapper handleBusinessException(BusinessException e) {
        log.error("业务异常=====>{}",e);
        return ResultWrapper.responseFail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultWrapper handleException(Exception e) {
        log.error("系统异常=====>{}",e);
        return ResultWrapper.responseFail(e.getMessage());
    }
}
