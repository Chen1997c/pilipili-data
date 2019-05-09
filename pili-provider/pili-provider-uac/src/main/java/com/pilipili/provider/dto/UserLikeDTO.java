package com.pilipili.provider.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 描述：关注用户
 *
 * @author ChenJianChuan
 * @date 2019/4/19　17:43
 */
@Data
@ToString
public class UserLikeDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 封面
     */
    private String avatarUrl;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 签名
     */
    private String signature;

    /**
     * 是否关注
     */
    private Integer isLike;

    /**
     * 是否被关注
     */
    private Integer isLiked;

}
