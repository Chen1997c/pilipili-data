package com.pilipili.provider.dao;

import com.pilipili.provider.entity.Label;
import com.pilipili.provider.entity.VideoLabelRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述： videoLabelRel dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　15:04
 */
public interface VideoLabelRelDAO extends JpaRepository<VideoLabelRel, Long> {

    /**
     * 根据视频id查询所有标签
     * @param videoId
     * @return
     */
    @Query(value =  "select vlr.label from VideoLabelRel vlr where vlr.videoId=:videoId ")
    List<Label> findAllByVideoId(Long videoId);
}
