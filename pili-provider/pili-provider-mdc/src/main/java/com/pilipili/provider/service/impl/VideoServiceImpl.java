package com.pilipili.provider.service.impl;

import com.pilipili.common.util.BusinessException;
import com.pilipili.provider.dao.VideoDAO;
import com.pilipili.provider.dao.VideoLabelRelDAO;
import com.pilipili.provider.dto.VideoDTO;
import com.pilipili.provider.entity.Label;
import com.pilipili.provider.entity.Video;
import com.pilipili.provider.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述： video业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:53
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDAO videoDAO;
    @Autowired
    private VideoLabelRelDAO videoLabelRelDAO;

    @Override
    public List<VideoDTO> listRecommendVideo() {
        try {
            List<VideoDTO> videoDTOs = new ArrayList<>();
            List<Video> videos = videoDAO.findAllByRecommend(true);
            if (!CollectionUtils.isEmpty(videos)) {
                videos.forEach(video -> {
                    VideoDTO videoDTO = new VideoDTO();
                    List<Label> labels = videoLabelRelDAO.findAllByVideoId(video.getId());
                    videoDTO.setLabels(labels);
                    videoDTO.setVideo(video);
                    videoDTOs.add(videoDTO);
                });
            }
            return videoDTOs;
        } catch (Exception e) {
            throw new BusinessException("查询推荐视频失败", e);
        }
    }

    @Override
    public List<VideoDTO> listHotVideo() {
        return null;
    }
}
