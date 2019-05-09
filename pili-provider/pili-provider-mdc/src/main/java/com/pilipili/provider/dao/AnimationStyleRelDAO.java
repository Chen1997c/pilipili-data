package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationStyleRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： animationStyleRel dao
 *
 * @author ChenJianChuan
 * @date 2019/4/4 16:14
 */
public interface AnimationStyleRelDAO extends JpaRepository<AnimationStyleRel, Long> {

    /**
     * 根据番剧查询
     * @param animation
     * @return
     */
    List<AnimationStyleRel> findAllByAnimation(Animation animation);
}
