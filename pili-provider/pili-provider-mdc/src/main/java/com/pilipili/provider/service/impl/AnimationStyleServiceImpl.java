package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.AnimationStyleDAO;
import com.pilipili.provider.dao.AnimationStyleRelDAO;
import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationStyle;
import com.pilipili.provider.entity.AnimationStyleRel;
import com.pilipili.provider.service.AnimationStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述： 番剧风格业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/4　16:17
 */
@Service
public class AnimationStyleServiceImpl implements AnimationStyleService {

    @Autowired
    private AnimationStyleRelDAO animationStyleRelDao;
    @Autowired
    private AnimationStyleDAO animationStyleDAO;

    @Override
    public List<AnimationStyleRel> listAnimationStyle(Long animationId) {
        try {
            Animation animation = new Animation();
            animation.setId(animationId);
            return animationStyleRelDao.findAllByAnimation(animation);
        } catch (Exception e) {
            throw new BusinessException("查询番剧风格失败", e);
        }
    }

    @Override
    public List<AnimationStyle> listAllAnimationStyle() {
        try {
            return animationStyleDAO.findAll();
        }catch (Exception e) {
            throw new BusinessException("查询全部番剧风格失败", e);
        }
    }
}
