package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述： 评分列表dto
 *
 * @author ChenJianChuan
 * @date 2019/4/4　15:02
 */
@Data
@ToString
public class AnimationScoreDTO {

    /**
     * 评分分数
     */
    private Integer score;

    /**
     * 评分内容
     */
    private String content;

    /**
     * 评分时间
     */
    private Date scoreTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 集名
     */
    private String resName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;
}
