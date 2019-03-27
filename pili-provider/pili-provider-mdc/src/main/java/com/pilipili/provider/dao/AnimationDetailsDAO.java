package com.pilipili.provider.dao;

import com.pilipili.provider.dto.AnimationRandomDTO;
import com.pilipili.provider.entity.AnimationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述： animationDetails dao
 *
 * @author ChenJianChuan
 * @date 2019/3/27　14:30
 */
public interface AnimationDetailsDAO extends JpaRepository<AnimationDetails, Long> {

    /**
     * 查询全部
     * @return
     */
    @Query(value = "select new com.pilipili.provider.dto.AnimationRandomDTO(ad.animation.id,ad.animation.name,ad.episodes,ad.coverUrl) from AnimationDetails ad")
    List<AnimationRandomDTO> listAll();

    /**
     * 根据区域查询
     * @param districtCd
     * @return
     */
    @Query(value = "select new com.pilipili.provider.dto.AnimationRandomDTO(ad.animation.id,ad.animation.name,ad.episodes,ad.coverUrl) from AnimationDetails ad where ad.animation.districtCd=:districtCd")
    List<AnimationRandomDTO> listAllByDistrictCd(Integer districtCd);
}
