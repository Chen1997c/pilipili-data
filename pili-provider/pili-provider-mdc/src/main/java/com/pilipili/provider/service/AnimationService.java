package com.pilipili.provider.service;

import com.pilipili.common.response.PageObject;
import com.pilipili.provider.dto.*;
import com.pilipili.provider.vo.AnimationIndexSortVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 描述：番剧业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/26　20:14
 */
public interface AnimationService {

    /**
     * 获取推荐番剧
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<AnimationRecommendDTO> listRecommendAnimation(Integer pageNumber, Integer pageSize);

    /**
     * 随机获取3个
     * @param userId
     * @return
     */
    List<AnimationRandomDTO> listRandomThreeAnimation(Long userId);

    /**
     * 获取国内3个
     * @param userId
     * @return
     */
    List<AnimationRandomDTO> listInternalThreeAnimation(Long userId);

    /**
     * 获取番剧播放界面信息
     * @param userId
     * @param animationId
     * @return
     */
    AnimationPlayDTO getAnimationPlayInfo(Long userId, Long animationId);

    /**
     * 获取未喜欢的番剧
     * @param userId
     * @param pageNumber
     * @param PageSize
     * @return
     */
    PageObject<List<AnimationUnlikeDTO>> listAnimationsUnlike(Long userId, Integer pageNumber, Integer PageSize);

    /**
     * 获取用户对番剧评分、观看历史
     * @param userId
     * @param animationId
     * @return
     */
    AnimationUserSeeDTO getUserSeeInfo(Long userId, Long animationId);

    /**
     * 番剧索引
     * @param animationIndexSortVO
     * @return
     */
    PageObject<List<AnimationIndexDTO>> indexAnimation(AnimationIndexSortVO animationIndexSortVO);
}
