package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述：视频播放界面的推荐番剧(未喜欢)dto
 *
 * @author ChenJianChuan
 * @date 2019/3/30　11:28
 */
@Data
@ToString
public class AnimationUnlikeDTO {

    /**
     * 番剧id
     */
    private Long id;

    /**
     * 番剧名
     */
    private String name;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 播放量
     */
    private Long playAmount;

    /**
     * 总集数
     */
    private Integer episodes;

    /**
     * 评分人数
     */
    private Long scoreNbr;

    /**
     * 得分
     */
    private Double score;

    /**
     * 喜欢人数
     */
    private Long likeCount;
}
