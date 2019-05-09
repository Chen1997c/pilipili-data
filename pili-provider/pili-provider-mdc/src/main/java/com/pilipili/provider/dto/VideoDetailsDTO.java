package com.pilipili.provider.dto;

import com.pilipili.provider.entity.Label;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.Video;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 描述： 视频详情dto
 *
 * @author ChenJianChuan
 * @date 2019/4/7　11:26
 */
@Data
@ToString
public class VideoDetailsDTO {

    /**
     * 视频
     */
    private Video video;

    /**
     * 标签
     */
    private List<Label> labelList;

    /**
     * 投稿用户信息
     */
    private User user;

    /**
     * 点赞数量
     */
    private Long supportCount;

    /**
     * 收藏数量
     */
    private Long collectionCount;
}
