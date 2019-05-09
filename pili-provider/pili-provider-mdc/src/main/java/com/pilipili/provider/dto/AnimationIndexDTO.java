package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述： 番剧索引结果dto
 *
 * @author ChenJianChuan
 * @date 2019/4/12　0:05
 */
@Data
@ToString
public class AnimationIndexDTO {

    /**
     * 番剧id
     */
    private Long id;

    /**
     * 番剧名称
     */
    private String name;

    /**
     * 播放状态
     */
    private Integer playStatus;

    /**
     * 总集数
     */
    private Integer episodes;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 追番人数
     */
    private Long likeCount;
}
