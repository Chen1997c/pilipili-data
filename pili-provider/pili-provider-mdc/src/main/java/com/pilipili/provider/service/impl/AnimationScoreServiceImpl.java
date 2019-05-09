package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.response.PageObject;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.provider.dao.AnimationScoreDAO;
import com.pilipili.provider.dto.AnimationScoreDTO;
import com.pilipili.provider.entity.AnimationScore;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.service.AnimationScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 *
 * @author ChenJianChuan
 * @date 2019/4/4　15:12
 */
@Service
public class AnimationScoreServiceImpl implements AnimationScoreService {

    @Autowired
    private AnimationScoreDAO animationScoreDAO;

    @Override
    public PageObject<List<AnimationScoreDTO>> listAnimationScores(Long animationId, Integer pageNumber, Integer pageSize) {
        try {
            Page<Object[]> objects = animationScoreDAO.listAnimationScore(animationId, PageRequest.of(pageNumber, pageSize));
            if (objects.getTotalElements() == 0) {
                return null;
            }
            List<AnimationScoreDTO> animationScoreDTOS = ObjectUtil.transArrayToClass(objects.getContent(), AnimationScoreDTO.class);
            PageObject<List<AnimationScoreDTO>> pageObject = new PageObject<>();
            pageObject.setContent(animationScoreDTOS);
            pageObject.setCurrentPage(pageNumber);
            pageObject.setTotalPage(objects.getTotalPages());
            pageObject.setTotalElements(objects.getTotalElements());
            return pageObject;
        } catch (Exception e) {
            throw new BusinessException("查询番剧评分失败", e);
        }
    }


    @Override
    public void score(AnimationScore animationScore) {
        try {
            animationScoreDAO.getByAnimationIdAndUser(animationScore.getAnimationId(), animationScore.getUser())
                    .map(queryAnimationScore -> {
                        animationScore.setId(queryAnimationScore.getId());
                        return queryAnimationScore;
                    });
            animationScore.setScoreTime(new Date());
            animationScoreDAO.save(animationScore);
        } catch (Exception e) {
            throw new BusinessException("番剧评分失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void deleteScore(Long userId, Long animationId) {
        try {
            User user = new User();
            user.setId(userId);
            animationScoreDAO.getByAnimationIdAndUser(animationId, user)
                    .map(queryAnimationScore -> {
                        animationScoreDAO.deleteById(queryAnimationScore.getId());
                        return queryAnimationScore;
                    }).orElseThrow(() -> new BusinessException("删除番剧评分失败,未查询到评分记录"));
        } catch (Exception e) {
            throw new BusinessException("删除番剧评分失败", e);
        }
    }
}
