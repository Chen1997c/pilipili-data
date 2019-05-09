package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述： 收藏夹内视频dto
 *
 * @author ChenJianChuan
 * @date 2019/4/17　14:57
 */
@Data
@ToString
public class VideoInCollectionDTO {

    /**
     * 视频id
     */
    private Long id;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 视频时常
     */
    private String duration;

    /**
     * 标题
     */
    private String title;

    /**
     * 播放量
     */
    private Long playAmount;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 评论量
     */
    private Long commentCount;
}
