package com.pilipili.provider.service;

import com.pilipili.common.response.PageObject;
import com.pilipili.provider.dto.AnimationScoreDTO;
import com.pilipili.provider.entity.AnimationScore;

import java.util.List;

/**
 * 描述：番剧评分业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/26　20:14
 */
public interface AnimationScoreService {

    /**
     * 分页查询评分
     * @param animationId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageObject<List<AnimationScoreDTO>> listAnimationScores(Long animationId, Integer pageNumber, Integer pageSize);

    /**
     * 评分哟
     * @param animationScore
     */
    void score(AnimationScore animationScore);

    /**
     * 删除评分呐
     * @param userId
     * @param animationId
     */
    void deleteScore(Long userId, Long animationId);
}
