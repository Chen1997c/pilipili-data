package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoFavoriteCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： videoFavoriteCollection dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoFavoriteCollectionDAO extends JpaRepository<VideoFavoriteCollection, Long> {

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    List<VideoFavoriteCollection> findAllByUserId(Long userId);

    /**
     * 根据收藏夹删除
     * @param favoriteId
     */
    void deleteByVideoFavoriteId(Long favoriteId);

    /**
     * 根据userId和favoriteId查询
     * @param userId
     * @param favoriteId
     * @return
     */
    VideoFavoriteCollection findByUserIdAndVideoFavoriteId(Long userId, Long favoriteId);

}
