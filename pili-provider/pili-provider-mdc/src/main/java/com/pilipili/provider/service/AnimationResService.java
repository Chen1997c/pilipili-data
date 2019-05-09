package com.pilipili.provider.service;

import com.pilipili.provider.entity.AnimationRes;

import java.util.List;

/**
 * 描述：番剧资源业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/29　16:14
 */
public interface AnimationResService {

    /**
     * 根据animationId获取全部
     * @param animationId
     * @return
     */
    List<AnimationRes> listAllByAnimationId(Long animationId);
}
