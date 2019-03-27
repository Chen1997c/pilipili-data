package com.pilipili.provider.service;

import com.pilipili.provider.dto.AnimationRandomDTO;
import com.pilipili.provider.dto.AnimationRecommendDTO;

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
     * @return
     */
    List<AnimationRecommendDTO> listRecommendAnimation();

    /**
     * 随机获取3个
     * @return
     */
    List<AnimationRandomDTO> listRandomThreeAnimation();

    /**
     * 获取国内3个
     * @return
     */
    List<AnimationRandomDTO> listInternalThreeAnimation();
}
