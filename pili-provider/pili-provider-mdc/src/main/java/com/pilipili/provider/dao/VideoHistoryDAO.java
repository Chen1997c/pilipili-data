package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述： videoHistory dao
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:51
 */
public interface VideoHistoryDAO extends JpaRepository<VideoHistory, Long> {

    /**
     * 根据videoId列表和userId删除
     * @param userId
     * @param videoIdList
     */
    void deleteAllByUserIdAndAndVideoIdIn(Long userId, List<Long> videoIdList);

    /**
     * 根据videoId和userId查询
     * @param userId
     * @param videoId
     * @return
     */
    VideoHistory findByUserIdAndVideoId(Long userId, Long videoId);
}
