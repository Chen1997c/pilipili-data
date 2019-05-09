package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Animation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 描述： animation dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface AnimationDAO extends JpaRepository<Animation, Long> {

    /**
     * 查询播放界面的番剧基本信息
     * @param animationId
     * @param userId
     * @return
     */
    @Query(value = "select " +
            "a.id,ad.cover_url,ad.start_date,ad.origin_name,a.name,ad.play_amount,ad.profiles,ad.update_info,a.play_status,ad.episodes,a.district_cd," +
            "(select count(*) from animation_like al1 where a.id=al1.animation_id and al1.user_id=:userId) isLike," +
            "(select count(*) from animation_like al where a.id=al.animation_id ) likeCount," +
            "(select avg(as1.score ) from animation_score as1 where as1.animation_id=a.id) score," +
            "(select count(*) from animation_score as1 where as1.animation_id=a.id) scoreCount," +
            "(select count(*) from feeding f where f.feed_type=1 and f.ref_id=a.id) food " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.id=:animationId", nativeQuery = true)
    List<Object[]> getAnimationPlayInfo(Long animationId, Long userId);

    /**
     * 查询尚未喜欢的番剧 根据评分降序排序
     * @param userId
     * @param pageable
     * @return
     */
    @Query(value = "select a.id,a.name,ad.cover_url,ad.play_amount,ad.episodes, " +
            "count(t.score) score_nbr," +
            "avg(t.score) score," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "left join animation_details ad on a.id=ad.animation_id " +
            "left join " +
            "(select ans.score,ans.animation_id from animation_score ans ) t " +
            "on t.animation_id=a.id " +
            "where a.id not in " +
            "(select al.animation_id from animation_like al where al.user_id=:userId) " +
            "group by a.id,a.name,ad.cover_url,ad.play_amount,ad.episodes " +
            "order by score desc",
            countQuery = "select count(*) from  animation a " +
                    "where a.id not in " +
                    "(select al.animation_id from animation_like al where al.user_id=:userId)",
            nativeQuery = true)
    Page<Object[]> listAnimationsUnlike(Long userId, Pageable pageable);

    /**
     * 根据类型，播放状态，风格查询 根据喜欢人数降序排序
     * @param type
     * @param status
     * @param style
     * @param pageable
     * @return
     */
    @Query(value = "select a.id,a.name,a.play_status,ad.episodes,ad.cover_url," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.type like :type and a.play_status like :status " +
            "and a.id in " +
            "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) " +
            "order by like_count desc",
            countQuery = "select count(*) from animation a " +
                    "where a.type like :type and a.play_status like :status " +
                    "and a.id in " +
                    "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) ",
            nativeQuery = true)
    Page<Object[]> listAnimationWithStyleOrderByLikeCountDesc(String type, String status, String style, Pageable pageable);

    /**
     * 根据类型，播放状态，风格查询 根据喜欢人数升序排序
     * @param type
     * @param status
     * @param style
     * @param pageable
     * @return
     */
    @Query(value =  "select a.id,a.name,a.play_status,ad.episodes,ad.cover_url," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.type like :type and a.play_status like :status " +
            "and a.id in " +
            "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) " +
            "order by like_count asc",
            countQuery = "select count(*) from animation a " +
                    "where a.type like :type and a.play_status like :status " +
                    "and a.id in " +
                    "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) ",
            nativeQuery = true)
    Page<Object[]> listAnimationWithStyleOrderByLikeCountAsc(String type, String status, String style, Pageable pageable);

    /**
     * 根据类型，播放状态，风格查询 根据最后更新时间降序排序
     * @param type
     * @param status
     * @param style
     * @param pageable
     * @return
     */
    @Query(value =  "select a.id,a.name,a.play_status,ad.episodes,ad.cover_url," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.type like :type and a.play_status like :status " +
            "and a.id in " +
            "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) " +
            "order by a.last_update_time desc",
            countQuery = "select count(*) from animation a " +
                    "where a.type like :type and a.play_status like :status " +
                    "and a.id in " +
                    "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) ",
            nativeQuery = true)
    Page<Object[]> listAnimationWithStyleOrderByLastUpdateTimeDesc(String type, String status, String style, Pageable pageable);

    /**
     * 根据类型，播放状态，风格查询 根据最后更新时间升序排序
     * @param type
     * @param status
     * @param style
     * @param pageable
     * @return
     */
    @Query(value =  "select a.id,a.name,a.play_status,ad.episodes,ad.cover_url," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.type like :type and a.play_status like :status " +
            "and a.id in " +
            "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) " +
            "order by a.last_update_time asc",
            countQuery = "select count(*) from animation a " +
                    "where a.type like :type and a.play_status like :status " +
                    "and a.id in " +
                    "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) ",
            nativeQuery = true)
    Page<Object[]> listAnimationWithStyleOrderByLastUpdateTimeAsc(String type, String status, String style, Pageable pageable);

    /**
     * 根据类型，播放状态，风格查询 根据评分降序排序
     * @param type
     * @param status
     * @param style
     * @param pageable
     * @return
     */
    @Query(value =  "select a.id,a.name,a.play_status,ad.episodes,ad.cover_url," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.type like :type and a.play_status like :status " +
            "and a.id in " +
            "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) " +
            "order by (select avg(as2.score) from animation_score as2 where as2.animation_id=a.id) desc",
            countQuery = "select count(*) from animation a " +
                    "where a.type like :type and a.play_status like :status " +
                    "and a.id in " +
                    "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) ",
            nativeQuery = true)
    Page<Object[]> listAnimationWithStyleOrderByScoreDesc(String type, String status, String style, Pageable pageable);

    /**
     * 根据类型，播放状态，风格查询 根据评分升序排序
     * @param type
     * @param status
     * @param style
     * @param pageable
     * @return
     */
    @Query(value =  "select a.id,a.name,a.play_status,ad.episodes,ad.cover_url," +
            "(select count(*) from animation_like al where al.animation_id=a.id ) like_count " +
            "from animation a " +
            "join animation_details ad on a.id=ad.animation_id " +
            "where a.type like :type and a.play_status like :status " +
            "and a.id in " +
            "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) " +
            "order by (select avg(as2.score) from animation_score as2 where as2.animation_id=a.id) asc",
            countQuery = "select count(*) from animation a " +
                    "where a.type like :type and a.play_status like :status " +
                    "and a.id in " +
                    "(select asl.animation_id from animation_style_rel asl join animation_style as1 on asl.animation_style_id=as1.id where as1.id like :style) ",
            nativeQuery = true)
    Page<Object[]> listAnimationWithStyleOrderByScoreAsc(String type, String status, String style, Pageable pageable);
}
