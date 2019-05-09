package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.VideoDAO;
import com.pilipili.provider.dao.VideoHistoryDAO;
import com.pilipili.provider.entity.Video;
import com.pilipili.provider.entity.VideoHistory;
import com.pilipili.provider.service.VideoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * 描述： 视频历史业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/18　0:24
 */
@Service
public class VideoHistoryServiceImpl implements VideoHistoryService {

    @Autowired
    private VideoHistoryDAO videoHistoryDAO;
    @Autowired
    private VideoDAO videoDAO;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void updateHistory(Long userId, Long videoId) {
        try {
            VideoHistory videoHistory = videoHistoryDAO.findByUserIdAndVideoId(userId, videoId);
            if (videoHistory == null) {
                videoHistory = new VideoHistory();
                videoHistory.setUserId(userId);
                videoHistory.setVideoId(videoId);
            } else {
                videoHistory.setGmtModified(new Date());
            }
            videoHistoryDAO.save(videoHistory);
            // 增加播放量
            Video video = videoDAO.findById(videoId).orElseThrow(()->new BusinessException("增加播放量失败,视频不存在"));
            video.setPlayAmount(video.getPlayAmount() + 1L);
            videoDAO.save(video);
        } catch (Exception e) {
            throw new BusinessException("保存视频观看历史失败", e);
        }
    }
}
