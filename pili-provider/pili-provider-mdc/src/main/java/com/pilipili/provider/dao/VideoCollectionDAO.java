package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述： videoCollection dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoCollectionDAO extends JpaRepository<VideoCollection, Long> {


    /**
     * 根据videoId统计数量 按userId去重
     *
     * @param videoId
     * @return
     */
    @Query(value = "select count(distinct(vc.user_id)) from video_collection vc where vc.video_id=:videoId",
            nativeQuery = true)
    Long countDistinctUserIdByVideoId(Long videoId);

    /**
     * 根据userId，videoId查询
     *
     * @param userId
     * @param videoId
     * @return
     */
    List<VideoCollection> findByUserIdAndVideoId(Long userId, Long videoId);

    /**
     * 根据收藏夹id获取封面url
     *
     * @param userId
     * @param favoriteId
     * @return
     */
    @Query(value = "select cover_url " +
            "from video_collection vc  " +
            "join video v on vc.video_id=v.id " +
            "where vc.video_favorite_id=:favoriteId and vc.user_id=:userId " +
            "order by vc.gmt_modified desc limit 1 ", nativeQuery = true)
    String getCoverUrlByFavoriteId(Long userId, Long favoriteId);

    /**
     * 根据收藏夹id统计数量
     *
     * @param userId
     * @param favoriteId
     * @return
     */
    Long countAllByUserIdAndVideoFavoriteId(Long userId, Long favoriteId);

    /**
     * 查询收藏夹内的视频信息
     *
     * @param userId
     * @param favoriteId
     * @param pageable
     * @return
     */
    @Query(value = "select v.id,v.cover_url,v.duration,v.title,v.play_amount,u.nick_name, " +
            "(select count(*) from comment c where c.comment_type_code=2 and c.ref_id=v.id) commentCount " +
            "from video_collection vc " +
            "join video v on v.id=vc.video_id " +
            "join user u on u.id=v.post_user_id " +
            "where vc.user_id=:userId and vc.video_favorite_id=:favoriteId " +
            "order by vc.gmt_modified desc",
            countQuery = "select count(*) from video_collection vc " +
                    "where vc.user_id=:userId and vc.video_favorite_id=:favoriteId ",
            nativeQuery = true)
    Page<Object[]> findVideoInFavorite(Long userId, Long favoriteId, Pageable pageable);

    /**
     * 根据收藏夹id删除
     *
     * @param favoriteId
     */
    void deleteAllByVideoFavoriteId(Long favoriteId);

    /**
     * 根据userId，videoId，favoriteId删除
     *
     * @param userId
     * @param videoId
     * @param favoriteId
     */
    void deleteByUserIdAndVideoIdAndVideoFavoriteId(Long userId, Long videoId, Long favoriteId);

    /**
     * 根据userId和videoId查询
     *
     * @param userId
     * @param videoId
     * @return
     */
    @Query(value = "select vc.videoFavoriteId from VideoCollection vc where vc.userId=:userId and vc.videoId=:videoId")
    List<Long> findAllIdByUserIdAndVideoId(Long userId, Long videoId);
}
