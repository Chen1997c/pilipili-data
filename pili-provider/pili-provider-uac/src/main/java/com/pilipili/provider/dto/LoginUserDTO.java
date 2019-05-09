package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;
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
     * 性别 1男 2女 其他 保密
     */
    private Integer sexCode;

    @Column(columnDefinition = "date")
    private Date birthday;

    /**
     * 个性签名
     */
    private String signature;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态码(1 正常 0 不可用 -1 待删除)
     */
    private Integer statusCd;

    /**
     * 权限id列表
     */
    private Set<Long> privileges;
}
