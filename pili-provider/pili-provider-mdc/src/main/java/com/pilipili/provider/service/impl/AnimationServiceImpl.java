package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.exception.ResolveDataException;
import com.pilipili.common.response.PageObject;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.common.util.QueryUtil;
import com.pilipili.provider.dao.*;
import com.pilipili.provider.dto.*;
import com.pilipili.provider.entity.*;
import com.pilipili.provider.service.AnimationService;
import com.pilipili.provider.util.Constant;
import com.pilipili.provider.vo.AnimationIndexSortVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    private AnimationDAO animationDAO;
    @Autowired
    private AnimationScoreDAO animationScoreDAO;
    @Autowired
    private AnimationHistoryDAO animationHistoryDAO;
    @Autowired
    private AnimationResDAO animationResDAO;

    @Override
    public Page<AnimationRecommendDTO> listRecommendAnimation(Integer pageNumber, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return animationRecommendDAO.listAll(pageable);
        } catch (Exception e) {
            throw new BusinessException("查询编辑推荐失败", e);
        }
    }


    @Override
    public List<AnimationRandomDTO> listRandomThreeAnimation(Long userId) {
        try {
            List<AnimationRandomDTO> animationRandomDTOS = animationDetailsDAO.listAllByDistrictCd(Constant.JAPAN_DISTRICT_CODE, userId);
            return ObjectUtil.generateRandomDataNoRepeat(animationRandomDTOS, Constant.RANDOM_ANIMATION_COUNT);
        } catch (Exception e) {
            throw new BusinessException("查询番剧推荐失败", e);
        }
    }

    @Override
    public List<AnimationRandomDTO> listInternalThreeAnimation(Long userId) {
        try {
            List<AnimationRandomDTO> animationRandomDTOS = animationDetailsDAO.listAllByDistrictCd(Constant.INTERNAL_DISTRICT_CODE, userId);
            return ObjectUtil.generateRandomDataNoRepeat(animationRandomDTOS, Constant.RANDOM_ANIMATION_COUNT);
        } catch (Exception e) {
            throw new BusinessException("查询国创推荐失败", e);
        }
    }

    @Override
    public AnimationPlayDTO getAnimationPlayInfo(Long userId, Long animationId) {
        try {
            List<Object[]> objects = animationDAO.getAnimationPlayInfo(animationId, userId);
            List<AnimationPlayDTO> animationPlayDTOS = ObjectUtil.transArrayToClass(objects, AnimationPlayDTO.class);
            if (!CollectionUtils.isEmpty(animationPlayDTOS)) {
                return animationPlayDTOS.get(animationPlayDTOS.size() - 1);
            }
            return null;
        } catch (ResolveDataException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (Exception e) {
            throw new BusinessException("查询番剧信息失败", e);
        }
    }

    @Override
    public PageObject<List<AnimationUnlikeDTO>> listAnimationsUnlike(Long userId, Integer pageNumber, Integer PageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, PageSize);
            Page<Object[]> objects = animationDAO.listAnimationsUnlike(userId, pageable);
            List<AnimationUnlikeDTO> data = ObjectUtil.transArrayToClass(objects.getContent(), AnimationUnlikeDTO.class);
            return new PageObject<>(data, objects.getTotalPages(), pageNumber, objects.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("查询未喜欢番剧失败", e);
        }
    }

    @Override
    public AnimationUserSeeDTO getUserSeeInfo(Long userId, Long animationId) {
        try {
            AnimationUserSeeDTO animationUserSeeDTO = new AnimationUserSeeDTO();
            User user = new User();
            user.setId(userId);
            Optional<AnimationScore> scoreUser = animationScoreDAO.getByAnimationIdAndUser(animationId, user);
            scoreUser.map(s -> {
                animationUserSeeDTO.setScore(s.getScore());
                animationUserSeeDTO.setContent(s.getCommentMessage());
                return s.getUser();
            }).map(r -> {
                animationUserSeeDTO.setAvatarUrl(r.getAvatarUrl());
                return r;
            }).orElse(null);
            Animation animation = new Animation();
            animation.setId(animationId);
            List<AnimationRes> animationResList = animationResDAO.findAllByAnimation(animation);
            if(!CollectionUtils.isEmpty(animationResList)) {
                Optional<AnimationHistory> animationHistory = animationHistoryDAO.getByUserIdAndAndAnimationResIn(userId, animationResList);
                animationHistory.map(AnimationHistory::getAnimationRes).map(res -> {
                    animationUserSeeDTO.setHistoryName(res.getName());
                    animationUserSeeDTO.setHistoryEpisodeName(res.getEpisodeName());
                    return res;
                }).orElseThrow(() -> new BusinessException("查询用户用户对番剧评分、观看历史信息失败, 数据异常"));
            }
            return animationUserSeeDTO;
        } catch (Exception e) {
            throw new BusinessException("查询用户用户对番剧评分、观看历史信息失败", e);
        }
    }

    @Override
    public PageObject<List<AnimationIndexDTO>> indexAnimation(AnimationIndexSortVO animationIndexSortVO) {
        try {
            String type = QueryUtil.generateLikeStr(String.valueOf(animationIndexSortVO.getTypeRule()));
            String style = QueryUtil.generateLikeStr(String.valueOf(animationIndexSortVO.getStyleRule()));
            String status = QueryUtil.generateLikeStr(String.valueOf(animationIndexSortVO.getStatusRule()));
            Pageable pageable = PageRequest.of(animationIndexSortVO.getPageNumber(), animationIndexSortVO.getPageSize());
            Page<Object[]> pages;
            if(Constant.AVG_SCORE_SORT_CODE == animationIndexSortVO.getSortType()) {
                if(animationIndexSortVO.getSortRule() == Constant.SORT_DESC_CODE) {
                    pages = animationDAO.listAnimationWithStyleOrderByScoreDesc(type, status, style, pageable);
                } else {
                    pages = animationDAO.listAnimationWithStyleOrderByScoreAsc(type, status, style, pageable);
                }
            } else if(Constant.UPDATE_TIME_SORT_CODE == animationIndexSortVO.getSortType()) {
                if(animationIndexSortVO.getSortRule() == Constant.SORT_DESC_CODE) {
                    pages = animationDAO.listAnimationWithStyleOrderByLastUpdateTimeDesc(type, status, style, pageable);
                } else {
                    pages = animationDAO.listAnimationWithStyleOrderByLastUpdateTimeAsc(type, status, style, pageable);
                }
            }else {
                if(animationIndexSortVO.getSortRule() == Constant.SORT_DESC_CODE) {
                    pages = animationDAO.listAnimationWithStyleOrderByLikeCountDesc(type, status, style, pageable);
                } else {
                    pages = animationDAO.listAnimationWithStyleOrderByLikeCountAsc(type, status, style, pageable);
                }
            }
            PageObject<List<AnimationIndexDTO>> pageObject = new PageObject<>();
            pageObject.setCurrentPage(pages.getNumber());
            pageObject.setContent(ObjectUtil.transArrayToClass(pages.getContent(), AnimationIndexDTO.class));
            pageObject.setTotalPage(pages.getTotalPages());
            pageObject.setTotalElements(pages.getTotalElements());
            return pageObject;
        }catch (Exception e) {
            throw new BusinessException("番剧索引失败", e);
        }
    }
}
