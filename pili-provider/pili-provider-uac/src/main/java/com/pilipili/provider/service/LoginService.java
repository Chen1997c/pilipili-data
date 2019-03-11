package com.pilipili.provider.service;

import com.pilipili.provider.dto.LoginUserDTO;

/**
 * 描述：用户登录业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/5　11:00
 */
public interface LoginService {

    /**
     * 根据用户名做登录操作
     *
     * @param username
     * @return
     */
    LoginUserDTO login(String username);
}
