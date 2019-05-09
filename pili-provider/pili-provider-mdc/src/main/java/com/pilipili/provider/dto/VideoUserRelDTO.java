package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述： 视频和用户关系DTO
 *
 * @author ChenJianChuan
 * @date 2019/4/16　19:45
 */
@Data
@ToString
public class VideoUserRelDTO {

    /**
     * 是否点赞 1是 0否
     */
    private Integer isSupport;

    /**
     * 是否不喜欢 1是 0否
     */
    private Integer isOppose;

    /**
     * 是否收藏
     */
    private Integer isCollected;
}
