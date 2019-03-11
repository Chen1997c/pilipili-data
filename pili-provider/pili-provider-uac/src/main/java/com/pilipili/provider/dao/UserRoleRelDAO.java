package com.pilipili.provider.dao;

import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 描述： 用户角色关联DAO
 *
 * @author ChenJianChuan
 * @date 2019/3/5　11:17
 */
public interface UserRoleRelDAO extends JpaRepository<UserRoleRel, Long> {

    /**
     * 查询用户拥有的角色
     * @return
     */
    List<UserRoleRel> findAllByUser(User user);

    /**
     * 新增用户角色关系
     * @param userID
     * @param roleId
     */
    @Modifying
    @Transactional
    @Query(value = "insert into user_role_rel(user_id,role_id,gmt_update,gmt_modified) values(?1,?2,now(),now())", nativeQuery = true)
    void addUserRoleRel(Long userID, Long roleId);
}
