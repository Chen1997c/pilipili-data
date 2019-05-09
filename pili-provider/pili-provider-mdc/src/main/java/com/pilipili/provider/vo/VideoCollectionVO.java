package com.pilipili.provider.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 描述：视频收藏vo
 *
 * @author ChenJianChuan
 * @date 2019/4/18　16:45
 */
@Data
@ToString
public class VideoCollectionVO {

    private Long userId;

    private Long videoId;

    private List<Long> favoriteIdList;
}
