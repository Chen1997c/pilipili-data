package com.pilipili.provider.dao;

import com.pilipili.provider.entity.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述： videoComment dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface VideoCommentDAO extends JpaRepository<VideoComment, Long> {

    /**
     * 根据videoId统计记录条数
     * @param videoId
     * @return
     */
    Long countVideoCommentByVideoId(Long videoId);
}
