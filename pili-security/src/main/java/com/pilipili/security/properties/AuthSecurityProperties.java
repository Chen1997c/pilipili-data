package com.pilipili.security.properties;

/**
 * 描述： 安全配置参数
 *
 * @author ChenJianChuan
 * @date 2019/1/23　16:04
 */
public class AuthSecurityProperties {

    public static final String TOKEN_KEY_ACCESS_METHOD = "permitAll()";

    public static final String CHECK_TOKEN_ACCESS_METHOD = "isAuthenticated()";

    public static final String JWT_SIGN_KEY = "pilipili_app";
}
