package com.pilipili.provider.dto;

import com.pilipili.provider.entity.Label;
import com.pilipili.provider.entity.Video;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 描述： 视频dto
 *
 * @author ChenJianChuan
 * @date 2019/3/25　15:36
 */
@Data
@ToString
public class VideoDTO {

    /**
     * 视频实体
     */
    private Video video;

    /**
     * 标签列表
     */
    private List<Label> labels;

    /**
     * 评论量
     */
    private Long commentAmount;

    /**
     * 发布人昵称
     */
    private String nickName;

    /**
     * 头像url
     */
    private String avatarUrl;

}
