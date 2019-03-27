package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

/**
 * 描述： 登录用户
 *
 * @author ChenJianChuan
 * @date 2019/3/5　11:04
 */
@Data
@ToString
public class LoginUserDTO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像路径
     */
    private String avatarUrl;

    /**
     * 状态码(1 正常 0 不可用 -1 待删除)
     */
    private Integer statusCd;

    /**
     * 权限id列表
     */
    private Set<Long> privileges;
}
