package com.pilipili.security.properties;

/**
 * 描述： 客户端配置参数
 *
 * @author ChenJianChuan
 * @date 2019/2/2　14:20
 */
public class AuthClientProperties {

    public static final String AUTH_CLIENT_ID = "web";
    public static final String AUTH_CLIENT_SECRET = "web";
    public static final String AUTH_CLIENT_SCOPE = "x";
    public static final String[] AUTH_TYPES = {"password", "refresh_token"};
    public static final Integer AUTH_TOKEN_EXPIRE = 60 * 60 * 24 * 7;
}
