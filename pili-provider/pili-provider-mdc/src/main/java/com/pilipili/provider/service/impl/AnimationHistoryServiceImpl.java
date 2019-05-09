package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.AnimationDetailsDAO;
import com.pilipili.provider.dao.AnimationHistoryDAO;
import com.pilipili.provider.dao.AnimationResDAO;
import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationDetails;
import com.pilipili.provider.entity.AnimationHistory;
import com.pilipili.provider.entity.AnimationRes;
import com.pilipili.provider.service.AnimationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 描述： 番剧历史业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/10　9:55
 */
@Service
public class AnimationHistoryServiceImpl implements AnimationHistoryService {

    @Autowired
    private AnimationHistoryDAO animationHistoryDAO;
    @Autowired
    private AnimationResDAO animationResDAO;
    @Autowired
    private AnimationDetailsDAO animationDetailsDAO;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void updateHistory(Long userId, Long resId) {
        try {
            AnimationRes animationRes = new AnimationRes();
            animationRes.setId(resId);
            Optional<AnimationRes> animationResOptional = animationResDAO.findById(resId);
            animationResOptional.map(queryAnimationRes -> {
                Animation animation = queryAnimationRes.getAnimation();
                AnimationDetails animationDetails = animationDetailsDAO.findById(animation.getId()).orElseThrow(() -> new BusinessException("保存番剧观看历史失败"));
                animationDetails.setPlayAmount(animationDetails.getPlayAmount() + 1L);
                animationDetailsDAO.save(animationDetails);
                return animation;
            }).map(animation -> {
                List<AnimationRes> animationResList = animationResDAO.findAllByAnimation(animation);
                if (!CollectionUtils.isEmpty(animationResList)) {
                    animationHistoryDAO.deleteAllByUserIdAndAnimationResIn(userId, animationResList);
                }
                AnimationHistory animationHistory = new AnimationHistory();
                animationHistory.setUserId(userId);
                animationHistory.setAnimationRes(animationRes);
                animationHistoryDAO.save(animationHistory);
                return animation;
            }).orElseThrow(() -> new BusinessException("保存番剧观看历史失败"));
        } catch (Exception e) {
            throw new BusinessException("保存番剧观看历史失败", e);
        }
    }
}
