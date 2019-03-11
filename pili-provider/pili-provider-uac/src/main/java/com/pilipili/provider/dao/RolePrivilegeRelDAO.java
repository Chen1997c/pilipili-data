package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Role;
import com.pilipili.provider.entity.RolePrivilegeRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： 角色权限关联DAO
 *
 * @author ChenJianChuan
 * @date 2019/3/4　9:16
 */
public interface RolePrivilegeRelDAO extends JpaRepository<RolePrivilegeRel, Long> {

    /**
     * 查询用户拥有角色下的权限
     *
     * @param roles
     * @return
     */
    List<RolePrivilegeRel> findAllByRoleIn(List<Role> roles);
}
