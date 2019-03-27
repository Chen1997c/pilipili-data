package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Animation;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 描述： animation dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface AnimationDAO extends JpaRepository<Animation, Long> {

}
