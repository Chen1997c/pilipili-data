package com.pilipili.provider.service;

import com.pilipili.provider.entity.User;

/**
 * 描述： 用户业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/5　9:49
 */
public interface UserService {

    /**
     * 根据id获取
     * @param userId
     * @return
     */
    User getUserById(Long userId);
}
