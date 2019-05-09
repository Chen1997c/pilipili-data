package com.pilipili.provider.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 描述： 添加视频VO
 *
 * @author ChenJianChuan
 * @date 2019/4/14　20:57
 */
@Data
@ToString
public class VideoAddVO {

    /**
     * 视频地址
     */
    private String resUrl;

    /**
     * 封面图片
     */
    private MultipartFile coverFile;

    /**
     * 分区id
     */
    private Long channelSubId;

    /**
     * 标题
     */
    private String title;

    /**
     * 标签
     */
    private List<String> labelList;

    /**
     * 简介
     */
    private String profiles;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 时长
     */
    private String duration;

    /**
     * 是否需要作者授权 1是 0否
     */
    private Integer authRequired;
}
