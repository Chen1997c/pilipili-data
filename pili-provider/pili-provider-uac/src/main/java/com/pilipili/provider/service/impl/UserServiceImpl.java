package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.util.QueryUtil;
import com.pilipili.provider.dao.RoleDAO;
import com.pilipili.provider.dao.UserDAO;
import com.pilipili.provider.dao.UserLikeDAO;
import com.pilipili.provider.dao.UserRoleRelDAO;
import com.pilipili.provider.dto.LoginUserDTO;
import com.pilipili.provider.dto.UserLikeInfoDTO;
import com.pilipili.provider.dto.UserRoleDTO;
import com.pilipili.provider.entity.Role;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserRoleRel;
import com.pilipili.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 描述： 用户业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/26　9:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserLikeDAO userLikeDAO;
    @Autowired
    private UserRoleRelDAO userRoleRelDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public User getUserById(Long userId) {
        try {
            return userDAO.findById(userId).orElseThrow(() -> new BusinessException("未查询到该用户信息,id:" + userId));
        } catch (Exception e) {
            throw new BusinessException("获取用户信息失败");
        }
    }

    @Override
    public void updateUser(LoginUserDTO user) {
        try {
            if (user.getId() == null) {
                return;
            }
            Optional<User> optionalUser = userDAO.findById(user.getId());
            optionalUser.map(u -> {
                if (user.getAvatarUrl() != null) {
                    u.setAvatarUrl(user.getAvatarUrl());
                }
                if (user.getNickName() != null) {
                    User queryUser = userDAO.getByNickName(user.getNickName());
                    if (queryUser == null || user.getId().equals(queryUser.getId())) {
                        u.setNickName(user.getNickName());
                    } else {
                        throw new BusinessException("该昵称已存在");
                    }
                }
                u.setSexCode(user.getSexCode());
                u.setBirthday(user.getBirthday());
                u.setSignature(user.getSignature());
                userDAO.save(u);
                return u;
            }).orElseThrow(() -> new BusinessException("不得行哦"));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("更新用户信息失败");
        }
    }

    @Override
    public UserLikeInfoDTO getUserLikeInfo(Long userId, Long currentUserId) {
        UserLikeInfoDTO userLikeInfoDTO;
        try {
            User user = userDAO.findById(userId).orElseThrow(() -> new BusinessException("未查询到该用户信息,id:" + userId));
            userLikeInfoDTO = new UserLikeInfoDTO();
            userLikeInfoDTO.setId(user.getId());
            userLikeInfoDTO.setNickName(user.getNickName());
            userLikeInfoDTO.setAvatarUrl(user.getAvatarUrl());
            userLikeInfoDTO.setSexCode(user.getSexCode());
            userLikeInfoDTO.setSignature(user.getSignature());
            userLikeInfoDTO.setLoginName(user.getLoginName());
            userLikeInfoDTO.setEmail(user.getEmail());
            userLikeInfoDTO.setBirthday(user.getBirthday());
            Long likeCount = userLikeDAO.countAllByUser(user);
            userLikeInfoDTO.setLikeCount(likeCount);
            Long likedCount = userLikeDAO.countAllByLikeUser(user);
            userLikeInfoDTO.setLikedCount(likedCount);
            User currentUser = new User();
            currentUser.setId(currentUserId);
            if (userLikeDAO.findByUserAndLikeUser(currentUser, user) == null) {
                userLikeInfoDTO.setIsLike(0);
            } else {
                userLikeInfoDTO.setIsLike(1);
            }
            return userLikeInfoDTO;
        } catch (Exception e) {
            throw new BusinessException("查询用户信息失败", e);
        }
    }

    @Override
    public Page<UserRoleRel> queryUserList(Integer pageNumber, Integer pageSize, Integer statusCd, String nickName, Long roleId) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Specification<UserRoleRel> specification = (Specification<UserRoleRel>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicateList = new ArrayList<>();
                if (null != statusCd) {
                    predicateList.add(criteriaBuilder.equal(root.<User>get("user").<Integer>get("statusCd"), statusCd));
                }
                if (null != nickName) {
                    predicateList.add(criteriaBuilder.like(root.<User>get("user").get("nickName"), QueryUtil.generateLikeStr(nickName)));
                }
                if (null != roleId) {
                    predicateList.add(criteriaBuilder.equal(root.<Role>get("role").<Long>get("id"), roleId));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            };
            return userRoleRelDAO.findAll(specification, pageable);
        } catch (Exception e) {
            throw new BusinessException("查询用户失败", e);
        }
    }

    @Override
    public void updateStatus(Long id, Integer statusCd) {
        try {
            userDAO.findById(id).map(user -> {
                user.setStatusCd(statusCd);
                return userDAO.save(user);
            }).orElseThrow(() -> new BusinessException("更新状态失败，用户不存在"));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("更新用户状态失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public UserRoleDTO addUser(UserRoleDTO userRoleDTO) {
        try {
            if (CollectionUtils.isEmpty(userRoleDTO.getRoleList())) {
                return null;
            }
            if (userRoleDTO.getUser() != null) {
                User savedUser = userDAO.save(userRoleDTO.getUser());
                userRoleDTO.setUser(savedUser);
                List<Role> roleList =  new ArrayList<>();
                userRoleDTO.getRoleList().forEach(role -> {
                    // 添加角色
                    roleDAO.findById(role.getId()).map(queryRole -> {
                        roleList.add(queryRole);
                        UserRoleRel userRoleRel = new UserRoleRel();
                        userRoleRel.setUser(savedUser);
                        userRoleRel.setRole(queryRole);
                        return userRoleRelDAO.save(userRoleRel);
                    }).orElseThrow(() -> new BusinessException("添加角色失败，角色不存在,id:" + role.getId()));
                });
                userRoleDTO.setRoleList(roleList);
                return userRoleDTO;
            }
            return null;
        } catch (Exception e) {
            throw new BusinessException("保存用户失败", e);
        }
    }


}
