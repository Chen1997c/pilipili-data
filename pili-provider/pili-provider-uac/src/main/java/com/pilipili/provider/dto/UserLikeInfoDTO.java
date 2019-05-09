package com.pilipili.provider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pilipili.provider.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 描述： 包含关注信息的用户
 *
 * @author ChenJianChuan
 * @date 2019/4/20　10:34
 */
@Data
@ToString
public class UserLikeInfoDTO {

    /**
     * userId
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer sexCode;

    /**
     * 签名
     */
    private String signature;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 关注数
     */
    private Long likeCount;

    /**
     * 粉丝数
     */
    private Long likedCount;

    /**
     * 是否关注
     */
    private Integer isLike;
}
