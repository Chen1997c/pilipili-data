package com.pilipili.provider.service;

import com.pilipili.provider.dto.VideoFavoriteDTO;
import com.pilipili.provider.entity.VideoFavorite;

import java.util.List;

/**
 * 描述： 视频收藏夹业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/16　23:08
 */
public interface VideoFavoriteService {

    /**
     * 根据userId获取自创收藏夹
     * @param userId
     * @return
     */
    List<VideoFavoriteDTO> getCreateByUserId(Long userId);

    /**
     * 根据userId获取收藏收藏夹
     * @param userId
     * @return
     */
    List<VideoFavoriteDTO> getCollectByUserId(Long userId);

    /**
     * 更新
     * @param videoFavorite
     */
    void update(VideoFavorite videoFavorite);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 收藏和取消收藏切换
     * @param userId
     * @param favoriteId
     * @return
     */
    Integer changeFavorite(Long userId, Long favoriteId);

    /**
     * 新增
     * @param videoFavorite
     */
    void add(VideoFavorite videoFavorite);
}
