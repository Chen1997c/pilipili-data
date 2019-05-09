package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.RolePrivilegeRelDAO;
import com.pilipili.provider.dao.UserDAO;
import com.pilipili.provider.dao.UserRoleRelDAO;
import com.pilipili.provider.dto.LoginUserDTO;
import com.pilipili.provider.entity.Role;
import com.pilipili.provider.entity.RolePrivilegeRel;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserRoleRel;
import com.pilipili.provider.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述： 登录业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/5　11:01
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleRelDAO userRoleRelDAO;
    @Autowired
    private RolePrivilegeRelDAO rolePrivilegeRelDAO;

    @Override
    public LoginUserDTO login(String username) {
        try {
            // 查用户
            User user = userDAO.getByLoginNameOrEmailAndStatusCdIsNot(username, username, - 1);
            if (user != null) {
                //查角色
                List<UserRoleRel> userRoleRelList = userRoleRelDAO.findAllByUser(user);
                if (CollectionUtils.isEmpty(userRoleRelList)) {
                    throw new BusinessException("当前用户未分配角色");
                }
                List<Role> roles = new ArrayList<>();
                userRoleRelList.forEach(userRoleRel -> {
                    if (userRoleRel.getRole().getStatusCd() == 1) {
                        roles.add(userRoleRel.getRole());
                    }
                });
                //查权限
                List<RolePrivilegeRel> rolePrivilegeRelList = rolePrivilegeRelDAO.findAllByRoleIn(roles);
                if (CollectionUtils.isEmpty(rolePrivilegeRelList)) {
                    throw new BusinessException("当前用户下的所有角色都未分配权限");
                }
                Set<Long> privilegeIdSet = new HashSet<>();
                rolePrivilegeRelList.forEach(rolePrivilegeRel -> privilegeIdSet.add(rolePrivilegeRel.getPrivilege().getId()));
                LoginUserDTO loginUserDTO = new LoginUserDTO();
                BeanUtils.copyProperties(user, loginUserDTO);
                loginUserDTO.setPrivileges(privilegeIdSet);
                return loginUserDTO;
            }
            return null;
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("登录操作异常", e);
        }
    }
}
