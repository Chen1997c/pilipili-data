package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoOppose;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： videoOppose dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoOpposeDAO extends JpaRepository<VideoOppose, Long> {

    /**
     * 根据userId，videoId查询
     * @param userId
     * @param videoId
     * @return
     */
    VideoOppose findByUserIdAndVideoId(Long userId, Long videoId);
}
