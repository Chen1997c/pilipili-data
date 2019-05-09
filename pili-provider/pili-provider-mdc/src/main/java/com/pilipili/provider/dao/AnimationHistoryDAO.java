package com.pilipili.provider.dao;

import com.pilipili.provider.entity.AnimationHistory;
import com.pilipili.provider.entity.AnimationRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 描述：animationHistory dao
 *
 * @author ChenJianChuan
 * @date 2019/4/4　10:40
 */
public interface AnimationHistoryDAO extends JpaRepository<AnimationHistory, Long> {

    /**
     * 根据userId和animationRes列表查询
     * @param userId
     * @param animationResList
     * @return
     */
    Optional<AnimationHistory> getByUserIdAndAndAnimationResIn(Long userId, List<AnimationRes> animationResList);

    /**
     * 根据userId和animationRes查询
     * @param userId
     * @param animationRes
     * @return
     */
    AnimationHistory getByUserIdAndAnimationRes(Long userId, AnimationRes animationRes);

    /**
     * 根据userId和animationRes列表删除
     * @param userId
     * @param animationResList
     * @return
     */
    void deleteAllByUserIdAndAnimationResIn(Long userId, List<AnimationRes> animationResList);
}
