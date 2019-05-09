package com.pilipili.provider.service;

import com.pilipili.common.response.PageObject;
import com.pilipili.provider.dto.UserLikeDTO;
import com.pilipili.provider.entity.UserLike;

import java.util.List;

/**
 * 描述： 用户关注业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/5　9:49
 */
public interface UserLikeService {

    /**
     * 获取我的关注
     * @param userId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageObject<List<UserLikeDTO>> listMyLike(Long userId, Integer pageNumber, Integer pageSize);

    /**
     * 获取我的粉丝
     * @param userId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageObject<List<UserLikeDTO>> listLikeMe(Long userId, Integer pageNumber, Integer pageSize);

    /**
     * 关注或取消关注
     * @param userLike
     * @return
     */
    Integer changeLike(UserLike userLike);
}
