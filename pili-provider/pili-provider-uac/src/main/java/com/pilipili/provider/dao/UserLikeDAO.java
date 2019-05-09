package com.pilipili.provider.dao;

import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述：userLike dao
 *
 * @author ChenJianChuan
 * @date 2019/4/19　17:37
 */
public interface UserLikeDAO extends JpaRepository<UserLike, Long> {

    /**
     * 根据用户查询
     * @param user
     * @param pageable
     * @return
     */
    Page<UserLike> findAllByUser(User user, Pageable pageable);

    /**
     * 根据被关注用户查询
     * @param likeUser
     * @param pageable
     * @return
     */
    Page<UserLike> findAllByLikeUser(User likeUser, Pageable pageable);

    /**
     * 查询是否关注
     * @param user
     * @param likeUser
     * @return
     */
    UserLike findByUserAndLikeUser(User user, User likeUser);

    /**
     * 根据用户统计数量(关注数)
     * @param user
     * @return
     */
    Long countAllByUser(User user);

    /**
     * 根据被喜欢用户统计数量(粉丝数)
     * @param user
     * @return
     */
    Long countAllByLikeUser(User user);
}
