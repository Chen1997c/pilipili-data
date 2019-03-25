package com.pilipili.provider.service;

import com.pilipili.provider.dto.VideoDTO;

import java.util.List;

/**
 * 描述： video业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
public interface VideoService {

    /**
     * 获取推荐视频
     * @return
     */
    List<VideoDTO> listRecommendVideo();

    /**
     * 获取热门视频
     * @return
     */
    List<VideoDTO> listHotVideo();
}
