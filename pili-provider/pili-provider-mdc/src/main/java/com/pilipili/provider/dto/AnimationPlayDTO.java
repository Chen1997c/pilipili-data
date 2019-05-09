package com.pilipili.provider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

/**
 * 描述：番剧播放界面的番剧信息dto
 *
 * @author ChenJianChuan
 * @date 2019/3/29　9:56
 */
@Data
@ToString
public class AnimationPlayDTO {

    /**
     * 番剧id
     */
    private Long id;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 开播日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 原名
     */
    private String originName;

    /**
     * 番剧名
     */
    private String name;

    /**
     * 播放量
     */
    private Long playAmount;

    /**
     * 简介
     */
    private String profiles;

    /**
     * 更新信息
     */
    private String updateInfo;

    /**
     * 播放状态
     */
    private Integer playStatus;

    /**
     * 总集数
     */
    private Integer episodes;

    /**
     * 区域编码
     */
    private Integer districtCd;

    /**
     * 是否喜欢 有数据则是
     */
    private Long isLike;

    /**
     * 追番人数
     */
    private Long likeCount;

    /**
     * 得分
     */
    private Double score;

    /**
     * 打分人数
     */
    private Long scoreCount;

    /**
     * 投食量
     */
    private Long food;
}
