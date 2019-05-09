package com.pilipili.provider.service;

import com.pilipili.common.response.PageObject;
import com.pilipili.provider.dto.*;
import com.pilipili.provider.entity.Video;
import com.pilipili.provider.vo.VideoAddVO;
import org.springframework.data.domain.Page;

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
     *
     * @return
     */
    List<VideoDTO> listRecommendVideo();

    /**
     * 获取热门视频
     *
     * @return
     */
    List<VideoDTO> listHotVideo();

    /**
     * 获取视频详情
     *
     * @param videoId
     * @return
     */
    VideoDetailsDTO getVideoDetails(Long videoId);

    /**
     * 添加视频
     *
     * @param videoAddVO
     * @return
     */
    Long addVideo(VideoAddVO videoAddVO);

    /**
     * 获取用户观看记录(今天、昨天)
     *
     * @param userId
     * @param timeCode 1今天 0昨天
     * @return
     */
    List<SeeHistoryDTO> getUserHistory(Long userId, Integer timeCode);

    /**
     * 获取用户观看记录(昨天之前)
     * @param pageNumber
     * @param pageSize
     * @param userId
     * @return
     */
    PageObject<List<SeeHistoryDTO>> getUserHistoryEarlier(Integer pageNumber, Integer pageSize, Long userId);

    /**
     * 删除观看历史
     * @param userId
     * @param videoIdList
     * @param animeIdList
     */
    void deleteHistory(Long userId, List<Long> videoIdList, List<Long> animeIdList);

    /**
     * 根据状态获取用户的视频
     * @param userId
     * @param statusCd
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @return
     */
    Page<Video> getUserVideos(Long userId, Integer statusCd, Integer pageNumber, Integer pageSize, Integer sortBy);

    /**
     * 根据videoId删除
     * @param videoId
     */
    void deleteVideoById(Long videoId);

    /**
     * 根据videoId，userId获取视频用户关联信息
     * @param userId
     * @param videoId
     * @return
     */
    VideoUserRelDTO getVideoUserRel(Long userId, Long videoId);

    /**
     * 获取收藏夹内的视频列表
     * @param userId
     * @param favoriteId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageObject<List<VideoInCollectionDTO>> getVideoInFavorite(Long userId, Long favoriteId,Integer pageNumber, Integer pageSize);
}
