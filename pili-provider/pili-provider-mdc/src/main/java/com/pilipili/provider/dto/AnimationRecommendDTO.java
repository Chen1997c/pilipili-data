package com.pilipili.provider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 描述：番剧推荐dto
 *
 * @author ChenJianChuan
 * @date 2019/3/27　9:57
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnimationRecommendDTO {

    /**
     * 番剧id
     */
    private Long id;

    /**
     * 推荐封面
     */
    private String recommendCoverUrl;

    /**
     * 番剧名
     */
    private String name;

    /**
     * 简介
     */
    private String profile;

    /**
     * 最后更新时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date lastUpdateTime;
}
