package com.pilipili.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述： JSON封装类
 *
 * @author ChenJianChuan
 * @date 2019/3/5　10:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper {

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;


    public static ResultWrapper responseSuccess(Object data) {
        return new ResultWrapper(Constant.RETURN_CODE_SUCCESS, null, data);
    }

    public static ResultWrapper responseSuccess(String message, Object data) {
        return new ResultWrapper(Constant.RETURN_CODE_SUCCESS, message, data);
    }

    public static ResultWrapper responseSuccess() {
        return new ResultWrapper(Constant.RETURN_CODE_SUCCESS, Constant.SUCCESS_MSG, null);
    }

    public static ResultWrapper responseFail(String message) {
        return new ResultWrapper(Constant.RETURN_CODE_FAIL, message, null);
    }
}
