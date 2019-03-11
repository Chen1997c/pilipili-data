package com.pilipili.provider.service.impl;

import com.pilipili.common.util.BusinessException;
import com.pilipili.provider.dao.UserDAO;
import com.pilipili.provider.dao.UserRoleRelDAO;
import com.pilipili.provider.entity.Role;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserRoleRel;
import com.pilipili.provider.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 描述： 注册业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:14
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleRelDAO userRoleRelDAO;

    @Override
    public Integer checkLoginNameIsUsed(String loginName) {
        try {
            return userDAO.getByLoginNameAndStatusCdIsNot(loginName,-1) == null ? 1 : 0;
        }catch (Exception e) {
            throw new BusinessException("查询用户名是否存在失败",e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void register(User user) {
        try {
            User savedUser = userDAO.save(user);
            userRoleRelDAO.addUserRoleRel(savedUser.getId(),10001L);
        }catch (Exception e) {
            throw new BusinessException("用户注册保存用户信息失败",e);
        }
    }
}
