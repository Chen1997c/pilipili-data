package com.pilipili.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 描述：随机推荐番剧dto
 *
 * @author ChenJianChuan
 * @date 2019/3/27　11:22
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnimationRandomDTO {


    /**
     * 番剧id
     */
    private Long id;

    /**
     * 番剧名
     */
    private String name;

    /**
     * 集数
     */
    private Integer episodes;

    /**
     * 封面url
     */
    private String coverUrl;

    /**
     * 是否追番了 不为空就是
     */
    private Long isLike;
}
