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
     * 根据登录名或者邮箱查询
     *
     * @param loginName
     * @param email
     * @param statusCd
     * @return
     */
    User getByLoginNameOrEmailAndStatusCdIsNot(String loginName, String email, int statusCd);

    /**
     * 根据昵称查询
     * @param nickName
     * @return
     */
    User getByNickName(String nickName);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    User getByEmail(String email);

    /**
     * 根据用户名查询
     * @param loginName
     * @param i
     * @return
     */
    Integer getByLoginNameAndStatusCdIsNot(String loginName, int i);

}
