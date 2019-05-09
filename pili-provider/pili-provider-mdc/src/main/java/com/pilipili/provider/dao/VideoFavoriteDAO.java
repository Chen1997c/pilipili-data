package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述： videoFavorite dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoFavoriteDAO extends JpaRepository<VideoFavorite, Long> {

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    List<VideoFavorite> findAllByUserId(Long userId);

    /**
     * 根据id获取公开的
     * @param id
     * @return
     */
    @Query(value = "select vf from VideoFavorite vf where vf.id=:id and vf.isPublic=1")
    VideoFavorite getPulicById(Long id);
}
