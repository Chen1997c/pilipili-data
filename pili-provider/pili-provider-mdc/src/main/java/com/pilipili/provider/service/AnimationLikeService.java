package com.pilipili.provider.service;

import com.pilipili.common.response.PageObject;
import com.pilipili.provider.dto.AnimationLikeDTO;
import com.pilipili.provider.entity.AnimationLike;

import java.util.List;

/**
 * 描述：番剧喜欢业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/28　15:01
 */
public interface AnimationLikeService {

    /**
     * 获取追的番
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageObject<List<AnimationLikeDTO>> listUserLikeAnimations(Long userId, Integer pageNumber, Integer pageSize);

    /**
     * 追番或者取消朱覅按
     * @param animationLike
     * @return
     */
    Integer toggleLike(AnimationLike animationLike);
}
