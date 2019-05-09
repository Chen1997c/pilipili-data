package com.pilipili.security.web.controller;

import com.pilipili.security.authentication.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 当前用户信息API
 *
 * @author ChenJianChuan
 * @date 2019/3/7　19:19
 */
@RestController
public class UserDetailController {

    @GetMapping("/currentUser")
    public SecurityUser getCurrentUser() {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) auth2Authentication.getUserAuthentication().getPrincipal();
        securityUser.setPassword("");
        return securityUser;
    }
}
