package com.pilipili.provider.util;

/**
 * 描述： 常量定义
 *
 * @author ChenJianChuan
 * @date 2019/3/30　15:58
 */
public class Constant {

    /**
     * 推荐番剧量
     */
    public static final int RANDOM_ANIMATION_COUNT = 3;

    /**
     * 国内区域代码
     */
    public static final int INTERNAL_DISTRICT_CODE = 1;

    /**
     * 日本区域代码
     */
    public static final int JAPAN_DISTRICT_CODE = 2;


    public static final int COMMENT_VIDEO_TYPE_CODE = 2;


    public static final int UPDATE_TIME_SORT_CODE = 2;

    public static final int AVG_SCORE_SORT_CODE = 3;

    public static final int SORT_DESC_CODE = 1;


    public static final String VIDEO_TYPE = ".mp4";

    public static final String IMAGES_TYPE = ".jpg,.jpeg,.png";

    /**
     * 视频通过审核状态码
     */
    public static final int VIDEO_STATUS_ACCESS = 1;
    /**
     * 视频已删除状态码
     */
    public static final int VIDEO_STATUS_DELETE = -2;

    /**
     * 默认收藏夹id
     */
    public final static long DEFAULT_FAVORITE_ID = 0L;

    /**
     * 公开收藏夹代码
     */
    public final static int PUBLIC_CODE = 1;

    public final static String DEFAULT_FAVORITE_NAME = "默认收藏夹";
}
