package com.pilipili.common.util;

/**
 * 描述： 查询工具类
 *
 * @author ChenJianChuan
 * @date 2019/4/11　21:03
 */
public class QueryUtil {

    /**
     * 拼接like查询
     *
     * @return
     */
    public static String generateLikeStr(String str) {
        str = str == "null" ? "" : str;
        return '%' + str + '%';
    }
}
