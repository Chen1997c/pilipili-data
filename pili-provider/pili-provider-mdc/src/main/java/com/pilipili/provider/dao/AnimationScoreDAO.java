package com.pilipili.provider.dao;

import com.pilipili.provider.entity.AnimationScore;
import com.pilipili.provider.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * 描述： animationScore dao
 *
 * @author ChenJianChuan
 * @date 2019/3/29　16:07
 */
public interface AnimationScoreDAO extends JpaRepository<AnimationScore, Long> {

    /**
     * 根据番剧id和用户查询
     * @param animationId
     * @param user
     * @return
     */
    Optional<AnimationScore> getByAnimationIdAndUser(Long animationId, User user);

    /**
     * 根据番剧id查询
     * @param animationId
     * @param pageable
     * @return
     */
    @Query(value = "select ans.score,ans.comment_message,ans.score_time,u.id,t.name,u.nick_name,u.avatar_url from animation_score ans " +
            "left join (select ar1.name,ah.user_id from animation_history ah " +
            "join animation_res ar1 on ar1.id=ah.animation_res_id " +
            "where ah.animation_res_id " +
            "in (select ar.id from animation_res ar where ar.animation_id=:animationId)) t " +
            "on ans.user_id = t.user_id " +
            "join user u on ans.user_id=u.id " +
            "where ans.animation_id=:animationId order by ans.score_time",
            countQuery = "select count(*) from animation_score ans where ans.animation_id=:animationId",
            nativeQuery = true)
    Page<Object[]> listAnimationScore(Long animationId, Pageable pageable);
}
