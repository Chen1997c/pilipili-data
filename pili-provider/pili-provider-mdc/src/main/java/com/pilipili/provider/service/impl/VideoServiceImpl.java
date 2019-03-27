package com.pilipili.provider.service.impl;

import com.pilipili.common.util.BusinessException;
import com.pilipili.common.util.ResultWrapper;
import com.pilipili.common.util.TimeUtil;
import com.pilipili.provider.dao.VideoCommentDAO;
import com.pilipili.provider.dao.VideoDAO;
import com.pilipili.provider.dao.VideoLabelRelDAO;
import com.pilipili.provider.dto.VideoDTO;
import com.pilipili.provider.entity.Label;
import com.pilipili.provider.entity.Video;
import com.pilipili.provider.feign.UserFeignService;
import com.pilipili.provider.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    @Autowired
    private VideoCommentDAO videoCommentDAO;
    @Autowired
    private UserFeignService userFeignService;

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
                    videoDTO.setCommentAmount(videoCommentDAO.countVideoCommentByVideoId(video.getId()));
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
        try {
            List<VideoDTO> videoDTOs = new ArrayList<>();
            List<Video> videos = videoDAO.findAllByPostDateAfterOrderByPlayAmountDesc(TimeUtil.getSpecificDate(-7));
            if (!CollectionUtils.isEmpty(videos)) {
                videos.forEach(video -> {
                    ResultWrapper resultWrapper = userFeignService.getUser(video.getPostUserId());
                    VideoDTO videoDTO = new VideoDTO();
                    if (resultWrapper.getData() != null) {
                        LinkedHashMap userMap = (LinkedHashMap) resultWrapper.getData();
                        videoDTO.setNickName(String.valueOf(userMap.get("nickName")));
                        videoDTO.setAvatarUrl(String.valueOf(userMap.get("avatarUrl")));
                    }
                    videoDTO.setVideo(video);
                    videoDTOs.add(videoDTO);
                });
            }
            return videoDTOs;
        }catch (Exception e) {
            throw new BusinessException("查询热门视频失败", e);
        }
    }
}
