package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 描述： video dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoDAO extends JpaRepository<Video, Long> {

    /**
     * 查询推荐
     *
     * @return
     */
    @Query(value = "select v from Video v where v.recommend=1 and v.statusCd=1")
    List<Video> findAllByRecommend();

    /**
     * 根据播放量和发布日期查询全部
     *
     * @param postDate
     * @return
     */
    @Query(value = "select v from Video v where v.postDate > :postDate and v.statusCd=1" +
            " order by v.playAmount desc")
    List<Video> findAllByPostDateAfterOrderByPlayAmountDesc(Date postDate);

    /**
     * 根据用户id查询观看记录 今天的
     *
     * @param userId
     * @return
     */
    @Query(value = "select t.* FROM " +
            "(select 'video' type,v.id,null res_id,null see_order,v.title,'' name,v.cover_url,v.duration,u.nick_name,vh.gmt_modified time from video_history vh " +
            "join video v on vh.video_id=v.id " +
            "join user u on u.id=v.post_user_id " +
            "where vh.user_id= :userId " +
            "union all " +
            "select 'anime' type,a.id,ar.id res_id,ar.play_order see_order,a.name title, ar.name,case when case when ar.cover_url is NULL then ad.cover_url ELSE ar.cover_url end is NULL then ad.cover_url ELSE case when ar.cover_url is NULL then ad.cover_url ELSE ar.cover_url end end,ar.duration,'' nick_name,ah.gmt_modified time from animation_history ah " +
            "join animation_res ar on ar.id=ah.animation_res_id " +
            "join animation a on a.id=ar.animation_id " +
            "join animation_details ad on ad.animation_id=a.id "+
            "where ah.user_id= :userId) t " +
            "where t.time >= concat(substring_index(now(),' ',1), ' 00:00:00') order by t.time desc", nativeQuery = true)
    List<Object[]> findAllHistoryByUserIdToday(Long userId);

    /**
     * 根据用户id查询观看记录 昨天的
     *
     * @param userId
     * @return
     */
    @Query(value = "select t.* FROM " +
            "(select 'video' type,v.id,null res_id,null see_order,v.title,'' name,v.cover_url,v.duration,u.nick_name,vh.gmt_modified time from video_history vh " +
            "join video v on vh.video_id=v.id " +
            "join user u on u.id=v.post_user_id " +
            "where vh.user_id= :userId " +
            "union all " +
            "select 'anime' type,a.id,ar.id res_id,ar.play_order see_order,a.name title, ar.name,case when ar.cover_url is NULL then ad.cover_url ELSE ar.cover_url end,ar.duration,'' nick_name,ah.gmt_modified time from animation_history ah " +
            "join animation_res ar on ar.id=ah.animation_res_id " +
            "join animation a on a.id=ar.animation_id " +
            "join animation_details ad on ad.animation_id=a.id "+
            "where ah.user_id= :userId) t " +
            "where t.time between concat(substring_index(date_sub(now(), interval 1 day),' ',1), ' 00:00:00') " +
            "and concat(substring_index(now(),' ',1), ' 00:00:00') order by t.time desc", nativeQuery = true)
    List<Object[]> findAllHistoryByUserIdYestoday(Long userId);

    /**
     * 根据用户id查询观看记录 更早的
     *
     * @param pageable
     * @param userId
     * @return
     */
    @Query(value = "select t.* FROM " +
            "(select 'video' type,v.id,null res_id,null see_order,v.title,'' name,v.cover_url,v.duration,u.nick_name,vh.gmt_modified time from video_history vh " +
            "join video v on vh.video_id=v.id " +
            "join user u on u.id=v.post_user_id " +
            "where vh.user_id= :userId " +
            "union all " +
            "select 'anime' type,a.id,ar.id res_id,ar.play_order see_order,a.name title, ar.name,case when ar.cover_url is NULL then ad.cover_url ELSE ar.cover_url end,ar.duration,'' nick_name,ah.gmt_modified time from animation_history ah " +
            "join animation_res ar on ar.id=ah.animation_res_id " +
            "join animation a on a.id=ar.animation_id " +
            "join animation_details ad on ad.animation_id=a.id "+
            "where ah.user_id= :userId) t " +
            "where t.time < concat(substring_index(date_sub(now(), interval 1 day),' ',1), ' 00:00:00') order by t.time desc",
            countQuery = "select count(*) from  " +
                    "(select vh.gmt_modified time from  " +
                    "video_history vh " +
                    "where vh.user_id=:userId " +
                    "union all " +
                    "select ah.gmt_modified time from  " +
                    "animation_history ah " +
                    "where ah.user_id=:userId) t " +
                    "where t.time < concat(substring_index(date_sub(now(), interval 1 day),' ',1), ' 00:00:00') ",
            nativeQuery = true)
    Page<Object[]> findAllHistoryByUserIdEarlier(Long userId, Pageable pageable);

    /**
     * 根据用户id查询
     * @param userId
     * @param pageable
     * @return
     */
    Page<Video> findAllByPostUserId(Long userId,Pageable pageable);

    /**
     * 根据状态、用户id查询
     * @param statusCd
     * @param userId
     * @param pageable
     * @return
     */
    Page<Video> findAllByStatusCdAndPostUserId(Integer statusCd,Long userId,Pageable pageable);

    /**
     * 根据视频id和状态查询
     * @param id
     * @param statusCd
     * @return
     */
    Optional<Video> findByIdAndStatusCd(Long id, Integer statusCd);

    /**
     * 根据视频id查询除了删除状态的视频
     * @param statusCdArray
     * @param userId
     * @param pageable
     * @return
     */
    Page<Video> findAllByStatusCdInAndPostUserId(Integer[] statusCdArray, Long userId, Pageable pageable);
}
