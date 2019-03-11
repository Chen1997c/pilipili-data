package com.pilipili.provider.dao;


import com.pilipili.provider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： 用户DAO
 *
 * @author ChenJianChuan
 * @date 2019/3/4　9:16
 */
public interface UserDAO extends JpaRepository<User, Long> {

    /**
     * 根据登录名查询
     *
     * @param loginName
     * @return
     */
    User getByLoginNameAndStatusCdIsNot(String loginName, int statusCd);

}
