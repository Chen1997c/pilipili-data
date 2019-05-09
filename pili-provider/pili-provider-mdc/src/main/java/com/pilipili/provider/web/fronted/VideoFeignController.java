package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.PageObject;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.*;
import com.pilipili.provider.entity.Video;
import com.pilipili.provider.service.VideoService;
import com.pilipili.provider.vo.VideoAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/details")
    public ResultWrapper getVideoDetails(Long videoId) {
        VideoDetailsDTO videoDetailsDTO = videoService.getVideoDetails(videoId);
        return ResultWrapper.responseSuccess(videoDetailsDTO);
    }

    @PostMapping("")
    public ResultWrapper addVideo(@RequestParam String resUrl,
                                  @RequestParam Long channelSubId,
                                  @RequestParam String title,
                                  @RequestParam List<String> labelList,
                                  @RequestParam Long userId,
                                  @RequestParam String duration,
                                  @RequestParam String profiles,
                                  @RequestParam Integer authRequired,
                                  @RequestParam(required = false) MultipartFile coverFile) {
        VideoAddVO videoAddVO = new VideoAddVO();
        videoAddVO.setResUrl(resUrl);
        videoAddVO.setChannelSubId(channelSubId);
        videoAddVO.setTitle(title);
        videoAddVO.setLabelList(labelList);
        videoAddVO.setUserId(userId);
        videoAddVO.setDuration(duration);
        videoAddVO.setProfiles(profiles);
        videoAddVO.setCoverFile(coverFile);
        videoAddVO.setAuthRequired(authRequired);
        Long videoId = videoService.addVideo(videoAddVO);
        return ResultWrapper.responseSuccess(videoId);
    }

    @GetMapping("/history-current")
    public ResultWrapper getCurrentHistory(@RequestParam Long userId,
                                           @RequestParam Integer timeCode) {
        List<SeeHistoryDTO> userHistory = videoService.getUserHistory(userId, timeCode);
        return ResultWrapper.responseSuccess(userHistory);
    }

    @GetMapping("/history-earlier")
    public ResultWrapper getEarlierHistory(@RequestParam Long userId,
                                           @RequestParam Integer pageNumber,
                                           @RequestParam Integer pageSize) {
        PageObject<List<SeeHistoryDTO>> userHistory = videoService.getUserHistoryEarlier(pageNumber, pageSize, userId);
        return ResultWrapper.responseSuccess(userHistory);
    }

    @DeleteMapping("/history")
    public ResultWrapper deleteHistory(@RequestParam Long userId,
                                       @RequestParam(required = false) List<Long> videoIdList,
                                       @RequestParam(required = false) List<Long> animeIdList) {
        videoService.deleteHistory(userId, videoIdList, animeIdList);
        return ResultWrapper.responseSuccess();
    }

    @GetMapping("/user")
    public ResultWrapper getUserVideos(@RequestParam Long userId,
                                       @RequestParam(required = false) Integer statusCd,
                                       @RequestParam Integer pageNumber,
                                       @RequestParam Integer pageSize,
                                       // 1 投稿时间 2 播放量
                                       @RequestParam Integer sortBy) {
        Page<Video> videos = videoService.getUserVideos(userId, statusCd, pageNumber, pageSize, sortBy);
        return ResultWrapper.responseSuccess(videos);
    }

    @DeleteMapping("")
    public ResultWrapper deleteVideoById(@RequestParam Long videoId) {
        videoService.deleteVideoById(videoId);
        return ResultWrapper.responseSuccess();
    }

    @GetMapping("/user-rel")
    public ResultWrapper getVideoUserRel(@RequestParam Long userId,
                                         @RequestParam Long videoId) {
        VideoUserRelDTO videoUserRelDTO = videoService.getVideoUserRel(userId, videoId);
        return ResultWrapper.responseSuccess(videoUserRelDTO);
    }

    @GetMapping("/favorite")
    public ResultWrapper getVideoInFavorite(@RequestParam Long userId,
                                            @RequestParam Long favoriteId,
                                            @RequestParam Integer pageNumber,
                                            @RequestParam Integer pageSize) {
        PageObject<List<VideoInCollectionDTO>> videoDTOS = videoService.getVideoInFavorite(userId, favoriteId, pageNumber, pageSize);
        return ResultWrapper.responseSuccess(videoDTOS);
    }

}
