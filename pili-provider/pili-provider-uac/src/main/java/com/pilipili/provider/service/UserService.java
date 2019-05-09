package com.pilipili.provider.service;

import com.pilipili.provider.dto.LoginUserDTO;
import com.pilipili.provider.dto.UserLikeInfoDTO;
import com.pilipili.provider.dto.UserRoleDTO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserRoleRel;
import org.springframework.data.domain.Page;

/**
 * 描述： 用户业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/5　9:49
 */
public interface UserService {

    /**
     * 根据id获取
     * @param userId
     * @return
     */
    User getUserById(Long userId);

    /**
     * 更新
     * @param user
     */
    void updateUser(LoginUserDTO user);

    /**
     * 获取包含关注信息的用户信息
     * @param userId
     * @param currentUserId
     * @return
     */
    UserLikeInfoDTO getUserLikeInfo(Long userId,Long currentUserId);


    /**
     * 查询用户
     * @param pageNumber
     * @param pageSize
     * @param statusCd
     * @param nickName
     * @param roleId
     * @return
     */
    Page<UserRoleRel> queryUserList(Integer pageNumber, Integer pageSize, Integer statusCd, String nickName, Long roleId);


    /**
     * 更新状态
     * @param id
     * @param statusCd
     */
    void updateStatus(Long id, Integer statusCd);

    /**
     * 添加用户
     * @param userRoleDTO
     * @return
     */
    UserRoleDTO addUser(UserRoleDTO userRoleDTO);
}
