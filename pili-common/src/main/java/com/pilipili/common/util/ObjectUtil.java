package com.pilipili.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述： 类型转换工具类
 *
 * @author ChenJianChuan
 * @date 2019/3/6　14:42
 */
@Slf4j
public class ObjectUtil {

    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * Object 转 实体类
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T objectConvertToClass(Object data, Class<T> clazz) {
        try {
            return mapper.convertValue(data, clazz);
        }catch (Exception e) {
            log.error("解析数据异常",e);
        }
        return null;
    }
}
