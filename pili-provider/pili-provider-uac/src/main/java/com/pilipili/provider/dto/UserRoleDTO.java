package com.pilipili.provider.dto;

import com.pilipili.provider.entity.Role;
import com.pilipili.provider.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 描述： 包含角色信息的用户
 *
 * @author ChenJianChuan
 * @date 2019/4/29　11:42
 */
@Data
@ToString
public class UserRoleDTO {

    /**
     * 用户实体
     */
    private User user;

    /**
     * 角色列表
     */
    private List<Role> roleList;
}
