package com.pilipili.provider.dto;

import com.pilipili.provider.entity.User;
import lombok.Data;
import lombok.ToString;

/**
 * 描述： 视频收藏夹dto
 *
 * @author ChenJianChuan
 * @date 2019/4/16　23:12
 */
@Data
@ToString
public class VideoFavoriteDTO {

    /**
     * 收藏夹id
     */
    private Long id;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 收藏夹名称
     */
    private String name;

    /**
     * 视频总数
     */
    private Long videoCounts;

    /**
     * 是否公开
     */
    private Integer isPublic;

    /**
     * 创建者实体
     */
    private User user;

    /**
     * 简介
     */
    private String profiles;
}
