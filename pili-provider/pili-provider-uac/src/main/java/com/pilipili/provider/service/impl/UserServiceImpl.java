package com.pilipili.provider.service.impl;

import com.pilipili.common.util.BusinessException;
import com.pilipili.provider.dao.UserDAO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述： 用户业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/26　9:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserById(Long userId) {
        try {
            return userDAO.findById(userId).orElseThrow(() -> new BusinessException("未查询到该用户信息,id:" + userId));
        } catch (Exception e) {
            throw new BusinessException("获取用户信息失败");
        }
    }
}
