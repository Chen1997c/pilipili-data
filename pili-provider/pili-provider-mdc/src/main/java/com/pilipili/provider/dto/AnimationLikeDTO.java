package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述： 番剧喜欢dto
 *
 * @author ChenJianChuan
 * @date 2019/3/28　15:28
 */
@Data
@ToString
public class AnimationLikeDTO {

    /**
     * 番剧id
     */
    private Long animationId;


    /**
     * 番剧名称
     */
    private String animationName;

    /**
     * 总集数
     */
    private Integer episodes;

    /**
     * 上次观看话的名称
     */
    private String seeName;

    /**
     * 上次观看话的集名
     */
    private String seeEpisodeName;

    /**
     * 资源封面
     */
    private String resCover;

    /**
     * 番剧封面
     */
    private String animationCover;

    /**
     * 观看至
     */
    private Integer seeOrder;

    /**
     * 更新至总集数
     */
    private Integer updateTo;

    /**
     * 最新一集名字
     */
    private String updateToName;

}
