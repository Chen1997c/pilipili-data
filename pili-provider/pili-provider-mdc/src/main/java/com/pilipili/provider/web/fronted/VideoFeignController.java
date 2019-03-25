package com.pilipili.provider.web.fronted;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.dto.VideoDTO;
import com.pilipili.provider.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：视频control
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:59
 */
@RestController
@RequestMapping("/video")
public class VideoFeignController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/recommend")
    public ResultWrapper listRecommendVideo() {
        List<VideoDTO> recommendVideos = videoService.listRecommendVideo();
        return ResultWrapper.responseSuccess(recommendVideos);
    }

    @GetMapping("/hot")
    public ResultWrapper listHotVideo() {
        List<VideoDTO> recommendVideos = videoService.listHotVideo();
        return ResultWrapper.responseSuccess(recommendVideos);
    }
}
