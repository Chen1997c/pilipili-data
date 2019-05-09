package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.exception.ResolveDataException;
import com.pilipili.common.response.PageObject;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.provider.dao.AnimationLikeDAO;
import com.pilipili.provider.dto.AnimationLikeDTO;
import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationLike;
import com.pilipili.provider.service.AnimationLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 描述： 番剧喜欢业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/28　15:03
 */
@Service
public class AnimationLikeServiceImpl implements AnimationLikeService {

    @Autowired
    private AnimationLikeDAO animationLikeDAO;

    @Override
    public PageObject<List<AnimationLikeDTO>> listUserLikeAnimations(Long userId, Integer pageNumber, Integer pageSize) {
        try {
            Page<Object[]> animationLikes = animationLikeDAO.listUserLikeAnimations(userId, PageRequest.of(pageNumber, pageSize));
            PageObject<List<AnimationLikeDTO>> pageObject = new PageObject<>();
            if (animationLikes.getTotalElements() > 0) {
                List<AnimationLikeDTO> animationLikeDTOS = ObjectUtil.transArrayToClass(animationLikes.getContent(), AnimationLikeDTO.class);
                pageObject.setContent(animationLikeDTOS);
            }
            pageObject.setTotalElements(animationLikes.getTotalElements());
            pageObject.setTotalPage(animationLikes.getTotalPages());
            pageObject.setCurrentPage(animationLikes.getNumber());
            return pageObject;
        } catch (ResolveDataException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException("查询用户喜欢番剧失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public Integer toggleLike(AnimationLike animationLike) {
        int result;
        try {
            AnimationLike queryAnimationLike = animationLikeDAO.findByUserIdAndAnimation(animationLike.getUserId(), animationLike.getAnimation());
            if (queryAnimationLike != null) {
                animationLikeDAO.deleteById(queryAnimationLike.getId());
                result = 0;
            } else {
                animationLikeDAO.save(animationLike);
                result = 1;
            }
            return result;
        } catch (Exception e) {
            throw new BusinessException("切换追番状态失败", e);
        }
    }
}
