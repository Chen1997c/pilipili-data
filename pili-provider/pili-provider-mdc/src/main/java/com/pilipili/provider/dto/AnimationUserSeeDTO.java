package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述：用户对番剧评分、观看历史dto
 *
 * @author ChenJianChuan
 * @date 2019/4/4　10:23
 */
@Data
@ToString
public class AnimationUserSeeDTO {

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 评分分数
     */
    private Integer score;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 历史观看名称
     */
    private String historyName;

    /**
     * 历史观看集名
     */
    private String historyEpisodeName;

}
