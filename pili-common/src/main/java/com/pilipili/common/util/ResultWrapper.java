package com.pilipili.common.util;

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
        return new ResultWrapper(200, null, data);
    }

    public static ResultWrapper responseFail(String message) {
        return new ResultWrapper(-1, message, null);
    }
}
