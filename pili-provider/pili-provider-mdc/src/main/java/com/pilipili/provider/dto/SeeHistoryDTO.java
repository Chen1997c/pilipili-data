package com.pilipili.provider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述： 观看记录dto
 *
 * @author ChenJianChuan
 * @date 2019/4/15　11:57
 */
@Data
@ToString
public class SeeHistoryDTO {

    /**
     * 类型
     */
    private String type;

    /**
     * id
     */
    private Long id;

    /**
     * 番剧资源id
     */
    private Long resId;

    /**
     * 番剧观看至
     */
    private Integer seeOrder;

    /**
     * 标题
     */
    private String title;

    /**
     * 观看至的集名
     */
    private String name;

    /**
     * 封面url
     */
    private String coverUrl;

    /**
     * 时长
     */
    private String duration;

    /**
     * 投稿用户昵称
     */
    private String nickName;

    /**
     * 观看时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date time;
}
