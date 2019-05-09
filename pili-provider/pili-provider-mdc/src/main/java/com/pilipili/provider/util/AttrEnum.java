package com.pilipili.provider.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述： 属性值枚举
 *
 * @author ChenJianChuan
 * @date 2019/4/16　11:18
 */
@AllArgsConstructor
public enum AttrEnum {

    /**
     * 视频排序 根据投稿时间
     */
    VIDEO_SORT_BY_TIME(1, "postDate"),
    /**
     * 视频排序 根据播放量
     */
    VIDEO_SORT_BY_PLAYAMOUNT(2, "playAmount");

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    /**
     * 代码
     */
    private Integer code;

    /**
     * 值
     */
    private String value;

}
