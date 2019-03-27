package com.pilipili.provider.service.impl;

import com.pilipili.common.util.BusinessException;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.provider.dao.AnimationDAO;
import com.pilipili.provider.dao.AnimationDetailsDAO;
import com.pilipili.provider.dao.AnimationRecommendDAO;
import com.pilipili.provider.dto.AnimationRandomDTO;
import com.pilipili.provider.dto.AnimationRecommendDTO;
import com.pilipili.provider.service.AnimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 描述： 番剧业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/26　20:15
 */
@Service
public class AnimationServiceImpl implements AnimationService {

    @Autowired
    private AnimationRecommendDAO animationRecommendDAO;
    @Autowired
    private AnimationDetailsDAO animationDetailsDAO;

    /**
     * 推荐番剧量
     */
    private static final int RANDOM_ANIME_COUNT = 3;

    /**
     * 国内区域代码
     */
    private static final int INTERNAL_DISTRICT_CODE = 1;

    @Override
    public List<AnimationRecommendDTO> listRecommendAnimation() {
        try {
            return animationRecommendDAO.listAll();
        } catch (Exception e) {
            throw new BusinessException("查询推荐番剧失败", e);
        }
    }

    @Override
    public List<AnimationRandomDTO> listRandomThreeAnimation() {
        try {
            List<AnimationRandomDTO> animationRandomDTOS = animationDetailsDAO.listAll();
            return ObjectUtil.generateRandomDataNoRepeat(animationRandomDTOS, RANDOM_ANIME_COUNT);
        } catch (Exception e) {
            throw new BusinessException("查询推荐番剧失败", e);
        }
    }

    @Override
    public List<AnimationRandomDTO> listInternalThreeAnimation() {
        try {
            List<AnimationRandomDTO> animationRandomDTOS = animationDetailsDAO.listAllByDistrictCd(INTERNAL_DISTRICT_CODE);
            return ObjectUtil.generateRandomDataNoRepeat(animationRandomDTOS, RANDOM_ANIME_COUNT);
        } catch (Exception e) {
            throw new BusinessException("查询国创推荐失败", e);
        }
    }
}
