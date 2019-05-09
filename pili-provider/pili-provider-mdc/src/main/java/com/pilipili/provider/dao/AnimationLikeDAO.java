package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 描述： animationLike dao
 *
 * @author ChenJianChuan
 * @date 2019/3/28　14:36
 */
public interface AnimationLikeDAO extends JpaRepository<AnimationLike, Long> {

    /**
     * 获取用户追番信息概览
     * @param userId
     * @param pageable
     * @return
     */
    @Query(value = "select a.id,a.name,ad.episodes,t.name see_name,t.episode_name,t.cover_url res_cover,ad.cover_url anime_cover,t.play_order see_order,(select count(*) from animation_res ar4 where ar4.animation_id=a.id) update_to, " +
            "(select ar3.name from animation_res ar3 where ar3.animation_id=a.id order by play_order desc limit 1) update_to_name " +
            "from animation_like al " +
            "join animation a on al.animation_id=a.id " +
            "join animation_details ad on ad.animation_id=a.id " +
            "left join (select * from animation_res ar1) t1 on t1.animation_id=a.id " +
            "left join (select ah.gmt_modified md,ar2.* from animation_history ah join animation_res ar2 on ah.animation_res_id=ar2.id where ah.user_id=:userId) t " +
            "on t.animation_id=a.id " +
            "where al.user_id=:userId " +
            "group by a.id,a.name,ad.episodes,t.name,t.episode_name,t.cover_url,ad.cover_url,t.play_order,t.md order by t.md desc",
            countQuery = "select count(*) from animation_like al where al.user_id=:userId",
            nativeQuery = true)
    Page<Object[]> listUserLikeAnimations(Long userId, Pageable pageable);

    /**
     * 根据userId，番剧查询
     * @param userId
     * @param animation
     * @return
     */
    AnimationLike findByUserIdAndAnimation(Long userId, Animation animation);
}
