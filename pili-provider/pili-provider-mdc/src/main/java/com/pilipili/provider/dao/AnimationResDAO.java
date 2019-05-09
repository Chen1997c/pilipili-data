package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： animationRes dao
 *
 * @author ChenJianChuan
 * @date 2019/3/29　16:07
 */
public interface AnimationResDAO extends JpaRepository<AnimationRes, Long> {

    /**
     * 根据animation查询
     * @param animation
     * @return
     */
    List<AnimationRes> findAllByAnimation(Animation animation);
}





















































