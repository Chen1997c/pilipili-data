package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoSupport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： videoSupport dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoSupportDAO extends JpaRepository<VideoSupport, Long> {

    /**
     * 根据videoId统计数量
     * @param videoId
     * @return
     */
    Long countAllByVideoId(Long videoId);

    /**
     * 根据userId，videoId查询
     * @param userId
     * @param videoId
     * @return
     */
    VideoSupport findByUserIdAndVideoId(Long userId, Long videoId);
}
