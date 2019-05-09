package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.response.PageObject;
import com.pilipili.provider.dao.UserLikeDAO;
import com.pilipili.provider.dto.UserLikeDTO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserLike;
import com.pilipili.provider.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 用户关注业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/19　17:34
 */
@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private UserLikeDAO userLikeDAO;

    @Override
    public PageObject<List<UserLikeDTO>> listMyLike(Long userId, Integer pageNumber, Integer pageSize) {
        PageObject<List<UserLikeDTO>> pageObject ;
        try {
            pageObject = new PageObject<>();
            User user = new User();
            user.setId(userId);
            Page<UserLike> userLikeList = userLikeDAO.findAllByUser(user, PageRequest.of(pageNumber, pageSize));
            pageObject.setTotalElements(userLikeList.getTotalElements());
            pageObject.setCurrentPage(userLikeList.getNumber());
            pageObject.setTotalPage(userLikeList.getTotalPages());
            if(CollectionUtils.isEmpty(userLikeList.getContent())) {
                return pageObject;
            }
            List<UserLikeDTO> userLikeDTOS = new ArrayList<>();
            userLikeList.forEach(userLike -> {
                User likeUser =  userLike.getLikeUser();
                if(likeUser != null) {
                    UserLikeDTO userLikeDTO = setValue(likeUser);
                    userLikeDTO.setIsLike(1);
                    if(userLikeDAO.findByUserAndLikeUser(likeUser,user) != null) {
                        userLikeDTO.setIsLiked(1);
                    } else {
                        userLikeDTO.setIsLiked(0);
                    }
                    userLikeDTOS.add(userLikeDTO);
                }
            });
            pageObject.setContent(userLikeDTOS);
            return pageObject;
        }catch (Exception e) {
            throw new BusinessException("查询我的关注失败", e);
        }
    }

    @Override
    public PageObject<List<UserLikeDTO>> listLikeMe(Long userId, Integer pageNumber, Integer pageSize) {
        PageObject<List<UserLikeDTO>> pageObject ;
        try {
            User likeUser = new User();
            pageObject = new PageObject<>();
            likeUser.setId(userId);
            Page<UserLike> pages = userLikeDAO.findAllByLikeUser(likeUser, PageRequest.of(pageNumber, pageSize));
            pageObject.setCurrentPage(pages.getNumber());
            pageObject.setTotalElements(pages.getTotalElements());
            pageObject.setTotalPage(pages.getTotalPages());
            if(CollectionUtils.isEmpty(pages.getContent())) {
                return pageObject;
            }
            List<UserLikeDTO> userLikeDTOS = new ArrayList<>();
            pages.getContent().forEach(userLike -> {
                User user =  userLike.getUser();
                if(user != null) {
                    UserLikeDTO userLikeDTO = setValue(user);
                    userLikeDTO.setIsLiked(1);
                    if(userLikeDAO.findByUserAndLikeUser(likeUser,user) != null) {
                        userLikeDTO.setIsLike(1);
                    } else {
                        userLikeDTO.setIsLike(0);
                    }
                    userLikeDTOS.add(userLikeDTO);
                }
            });
            pageObject.setContent(userLikeDTOS);
            return pageObject;
        }catch (Exception e) {
            throw new BusinessException("查询我的粉丝失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public Integer changeLike(UserLike userLike) {
        int result;
        try {
           UserLike queryUserLike =  userLikeDAO.findByUserAndLikeUser(userLike.getUser(), userLike.getLikeUser());
           if(queryUserLike == null) {
               userLikeDAO.save(userLike);
               result = 1;
           } else {
               userLikeDAO.deleteById(queryUserLike.getId());
               result = 0;
           }
           return result;
        } catch (Exception e) {
            throw new BusinessException("修改关注状态失败", e);
        }
    }

    private UserLikeDTO setValue(User user) {
        UserLikeDTO userLikeDTO = new UserLikeDTO();
        userLikeDTO.setUserId(user.getId());
        userLikeDTO.setAvatarUrl(user.getAvatarUrl());
        userLikeDTO.setNickName(user.getNickName());
        userLikeDTO.setSignature(user.getSignature());
        return userLikeDTO;
    }
}
