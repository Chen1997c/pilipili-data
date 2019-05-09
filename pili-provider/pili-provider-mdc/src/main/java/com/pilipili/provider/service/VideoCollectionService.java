package com.pilipili.provider.service;

import com.pilipili.provider.vo.VideoCollectionVO;

import java.util.List;

/**
 * 描述： 视频收藏业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
public interface VideoCollectionService {

    /**
     * 从收藏夹移除
     * @param userId
     * @param videoId
     * @param favoriteId
     */
    void deleteOne(Long userId, Long videoId, Long favoriteId);

    /**
     * 获取视频所在收藏夹
     * @param userId
     * @param videoId
     * @return
     */
    List<Long> getVideoIn(Long userId, Long videoId);

    /**
     * 编辑收藏
     * @param videoCollectionVO
     */
    String addVideoCollection(VideoCollectionVO videoCollectionVO);
}
