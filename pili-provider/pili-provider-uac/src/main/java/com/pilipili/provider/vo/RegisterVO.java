package com.pilipili.provider.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 描述： 注册vo
 *
 * @author ChenJianChuan
 * @date 2019/4/25　16:44
 */
@Data
@ToString
public class RegisterVO {

    /**
     * 邮箱验证码
     */
    private String emailCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}
