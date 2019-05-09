package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： 角色DAO
 *
 * @author ChenJianChuan
 * @date 2019/3/4　9:16
 */
public interface RoleDAO extends JpaRepository<Role, Long> {
}
