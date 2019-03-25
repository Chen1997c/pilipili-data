package com.pilipili.provider.service;

import com.pilipili.provider.entity.User;

/**
 * 描述：用户注册业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:10
 */
public interface RegisterService {

    /**
     * 判断用户名是否被使用
     *
     * @param loginName
     * @return
     */
    Integer checkLoginNameIsUsed(String loginName);

    /**
     * 普通用户注册
     * @param user
     */
    void register(User user);

    /**
     * 生成验证码 并发送邮件
     * @param email
     * @return
     */
    String generateEmailCode(String email);
}
