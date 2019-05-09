package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.AnimationResDAO;
import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationRes;
import com.pilipili.provider.service.AnimationResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述： 番剧资源业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/29　16:15
 */
@Service
public class AnimationResServiceImpl implements AnimationResService {

    @Autowired
    private AnimationResDAO animationResDAO;

    @Override
    public List<AnimationRes> listAllByAnimationId(Long animationId) {
        try {
            Animation animation = new Animation();
            animation.setId(animationId);
            return animationResDAO.findAllByAnimation(animation);
        }catch (Exception e) {
            throw new BusinessException("查询番剧资源失败", e);
        }
    }
}
