package com.pilipili.provider.service;

import com.pilipili.provider.entity.AnimationStyle;
import com.pilipili.provider.entity.AnimationStyleRel;

import java.util.List;

/**
 * 描述： 番剧风格业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/4　16:16
 */
public interface AnimationStyleService {

    /**
     * 根据番剧id获取风格
     * @param animationId
     * @return
     */
    List<AnimationStyleRel> listAnimationStyle(Long animationId);

    /**
     * 获取全部风格
     * @return
     */
    List<AnimationStyle> listAllAnimationStyle();
}
