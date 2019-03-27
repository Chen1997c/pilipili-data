package com.pilipili.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 描述： 日期工具类
 *
 * @author ChenJianChuan
 * @date 2019/3/26　9:28
 */
public class TimeUtil {

    /**
     * 获取当前日期前/后 n 天
     * @param amount
     * @return
     */
    public static Date getSpecificDate(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }
}
