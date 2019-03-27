package com.pilipili.provider.dao;

import com.pilipili.provider.dto.AnimationRecommendDTO;
import com.pilipili.provider.entity.AnimationRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述： animationRecommend dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface AnimationRecommendDAO extends JpaRepository<AnimationRecommend, Long> {

    /**
     * 关联查询所有
     * @return
     */
    @Query(value = "select new com.pilipili.provider.dto.AnimationRecommendDTO(a.id,ar.recommendCoverUrl,a.name,ad.profiles,a.lastUpdateTime) from AnimationRecommend  ar, Animation a, AnimationDetails ad where ar.animation.id=a.id and a.id = ad.animation.id ")
    List<AnimationRecommendDTO> listAll();

}
