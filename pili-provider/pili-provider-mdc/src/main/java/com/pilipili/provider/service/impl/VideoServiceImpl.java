package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.response.PageObject;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.common.util.TimeUtil;
import com.pilipili.provider.dao.*;
import com.pilipili.provider.dto.*;
import com.pilipili.provider.entity.*;
import com.pilipili.provider.feign.UserFeignService;
import com.pilipili.provider.service.VideoService;
import com.pilipili.provider.util.AttrEnum;
import com.pilipili.provider.util.Constant;
import com.pilipili.provider.util.SaveFileUtil;
import com.pilipili.provider.vo.VideoAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;

/**
 * 描述： video业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/23　14:53
 */
@Service
@CacheConfig(cacheNames = "videoService")
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDAO videoDAO;
    @Autowired
    private VideoLabelRelDAO videoLabelRelDAO;
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private ReplyDAO replyDAO;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private LabelDAO labelDAO;
    @Autowired
    private ChannelDAO channelDAO;
    @Autowired
    private ChannelSubDAO channelSubDAO;
    @Autowired
    private VideoHistoryDAO videoHistoryDAO;
    @Autowired
    private AnimationHistoryDAO animationHistoryDAO;
    @Autowired
    private VideoSupportDAO videoSupportDAO;
    @Autowired
    private VideoCollectionDAO videoCollectionDAO;
    @Autowired
    private VideoOpposeDAO videoOpposeDAO;
    @Autowired
    private VideoFavoriteCollectionDAO videoFavoriteCollectionDAO;

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.static-path.images.video_cover}")
    private String videoCoverPath;

    @Cacheable(value = "listRecommendVideo")
    @Override
    public List<VideoDTO> listRecommendVideo() {
        try {
            List<VideoDTO> videoDTOs = new ArrayList<>();
            List<Video> videos = videoDAO.findAllByRecommend();
            if (!CollectionUtils.isEmpty(videos)) {
                videos.forEach(video -> {
                    ChannelSub channelSub = video.getChannelSub();
                    String channelName = channelDAO.getOne(channelSub.getChannelId()).getChannelName();
                    channelName += "·" + video.getChannelSub().getChannelSubName();
                    channelSub.setChannelSubName(channelName);
                    video.setChannelSub(channelSub);
                    VideoDTO videoDTO = new VideoDTO();
                    List<Label> labels = videoLabelRelDAO.findAllByVideoId(video.getId());
                    videoDTO.setLabels(labels);
                    videoDTO.setVideo(video);
                    List<Comment> comments = commentDAO.findAllByRefIdAndCommentTypeCode(video.getId(), Constant.COMMENT_VIDEO_TYPE_CODE);
                    if (CollectionUtils.isEmpty(comments)) {
                        videoDTO.setCommentAmount(0L);
                    } else {
                        List<Long> commentIdList = new ArrayList<>();
                        comments.forEach(comment -> commentIdList.add(comment.getId()));
                        Long replyCount = replyDAO.countAllByCommentIdInAndIsDelete(commentIdList, 0);
                        videoDTO.setCommentAmount(comments.size() + replyCount);
                    }
                    videoDTOs.add(videoDTO);
                });
            }
            return videoDTOs;
        } catch (Exception e) {
            throw new BusinessException("查询推荐视频失败", e);
        }
    }

    @Cacheable(value = "listHotVideo")
    @Override
    public List<VideoDTO> listHotVideo() {
        try {
            List<VideoDTO> videoDTOs = new ArrayList<>();
            List<Video> videos = videoDAO.findAllByPostDateAfterOrderByPlayAmountDesc(TimeUtil.getSpecificDate(-70));
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
        } catch (Exception e) {
            throw new BusinessException("查询热门视频失败", e);
        }
    }

    @Override
    public VideoDetailsDTO getVideoDetails(Long videoId) {
        try {
            // 查询视频
            Optional<Video> optionalVideo = videoDAO.findByIdAndStatusCd(videoId, Constant.VIDEO_STATUS_ACCESS);
            Video video = optionalVideo.orElseThrow(() -> new BusinessException("视频已删除"));
            // 查询标签
            List<Label> labels = videoLabelRelDAO.findAllByVideoId(videoId);
            // 调用 用户账户中心 获取用户信息
            ResultWrapper resultWrapper = userFeignService.getUser(video.getPostUserId());
            VideoDetailsDTO videoDetailsDTO = new VideoDetailsDTO();
            if (resultWrapper.getData() != null) {
                User user = ObjectUtil.objectConvertToClass(resultWrapper.getData(), User.class);
                videoDetailsDTO.setUser(user);
            } else {
                throw new BusinessException("查询视频详情失败, 未能获取投稿用户信息");
            }
            // 查询点赞数量
            Long supportCount = videoSupportDAO.countAllByVideoId(videoId);
            videoDetailsDTO.setSupportCount(supportCount);
            // 查询收藏数量
            Long collectCount = videoCollectionDAO.countDistinctUserIdByVideoId(videoId);
            videoDetailsDTO.setCollectionCount(collectCount);

            videoDetailsDTO.setVideo(video);
            videoDetailsDTO.setLabelList(labels);
            return videoDetailsDTO;
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("查询视频详情失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public Long addVideo(VideoAddVO videoAddVO) {
        try {
            Video video = new Video();
            if (videoAddVO.getCoverFile() != null) {
                String coverUrl = SaveFileUtil.saveVideo(uploadPath, videoCoverPath, Constant.IMAGES_TYPE, videoAddVO.getCoverFile());
                video.setCoverUrl(coverUrl);
            }
            video.setResUrl(videoAddVO.getResUrl());
            ChannelSub channelSub = channelSubDAO.getOne(videoAddVO.getChannelSubId());
            video.setChannelSub(channelSub);
            video.setTitle(videoAddVO.getTitle());
            video.setProfiles(videoAddVO.getProfiles());
            video.setPostDate(new Date());
            video.setDuration(videoAddVO.getDuration());
            video.setPostUserId(videoAddVO.getUserId());
            video.setAuthRequired(videoAddVO.getAuthRequired());
            Video savedVideo = videoDAO.save(video);
            for (String labelName : videoAddVO.getLabelList()) {
                Label label = labelDAO.findByLabelName(labelName);
                if (label == null) {
                    label = new Label();
                    label.setLabelName(labelName);
                    label = labelDAO.save(label);
                }
                VideoLabelRel videoLabelRel = new VideoLabelRel();
                videoLabelRel.setVideoId(savedVideo.getId());
                videoLabelRel.setLabel(label);
                videoLabelRelDAO.save(videoLabelRel);
            }
            return savedVideo.getId();
        } catch (Exception e) {
            throw new BusinessException("保存视频失败", e);
        }
    }


    @Override
    public List<SeeHistoryDTO> getUserHistory(Long userId, Integer timeCode) {
        try {
            if (timeCode == 1) {
                return ObjectUtil.transArrayToClass(videoDAO.findAllHistoryByUserIdToday(userId), SeeHistoryDTO.class);
            }
            if (timeCode == 0) {
                return ObjectUtil.transArrayToClass(videoDAO.findAllHistoryByUserIdYestoday(userId), SeeHistoryDTO.class);
            }
            return null;
        } catch (Exception e) {
            throw new BusinessException("查询观看历史失败", e);
        }
    }

    @Override
    public PageObject<List<SeeHistoryDTO>> getUserHistoryEarlier(Integer pageNumber, Integer pageSize, Long userId) {
        try {
            Page<Object[]> pageResult = videoDAO.findAllHistoryByUserIdEarlier(userId, PageRequest.of(pageNumber, pageSize));
            List<SeeHistoryDTO> seeHistoryDTOS = ObjectUtil.transArrayToClass(pageResult.getContent(), SeeHistoryDTO.class);
            PageObject<List<SeeHistoryDTO>> pageObject = new PageObject<>();
            pageObject.setTotalElements(pageResult.getTotalElements());
            pageObject.setContent(seeHistoryDTOS);
            pageObject.setCurrentPage(pageResult.getNumber());
            pageObject.setTotalPage(pageResult.getTotalPages());
            return pageObject;
        } catch (Exception e) {
            throw new BusinessException("查询观看历史失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void deleteHistory(Long userId, List<Long> videoIdList, List<Long> animeIdList) {
        try {
            if (!CollectionUtils.isEmpty(videoIdList)) {
                videoHistoryDAO.deleteAllByUserIdAndAndVideoIdIn(userId, videoIdList);
            }
            if (!CollectionUtils.isEmpty(animeIdList)) {
                List<AnimationRes> animationResList = new ArrayList<>();
                animeIdList.forEach(res -> {
                    AnimationRes animationRes = new AnimationRes();
                    animationRes.setId(res);
                    animationResList.add(animationRes);
                });
                animationHistoryDAO.deleteAllByUserIdAndAnimationResIn(userId, animationResList);
            }
        } catch (Exception e) {
            throw new BusinessException("删除观看历史失败", e);
        }
    }

    @Override
    public Page<Video> getUserVideos(Long userId, Integer statusCd, Integer pageNumber, Integer pageSize, Integer sortBY) {
        String sortProperties = null;
        Pageable pageable;
        try {
            if (AttrEnum.VIDEO_SORT_BY_TIME.getCode().equals(sortBY)) {
                sortProperties = AttrEnum.VIDEO_SORT_BY_TIME.getValue();
            }
            if (AttrEnum.VIDEO_SORT_BY_PLAYAMOUNT.getCode().equals(sortBY)) {
                sortProperties = AttrEnum.VIDEO_SORT_BY_PLAYAMOUNT.getValue();
            }
            if (sortProperties != null) {
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sortProperties));
            } else {
                pageable = PageRequest.of(pageNumber, pageSize);
            }
            if (statusCd != null) {
                return videoDAO.findAllByStatusCdAndPostUserId(statusCd, userId, pageable);
            } else {
                Integer[] statusCdArray = {-1, 0, 1};
                return videoDAO.findAllByStatusCdInAndPostUserId(statusCdArray, userId, pageable);
            }
        } catch (Exception e) {
            throw new BusinessException("查询投稿视频失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void deleteVideoById(Long videoId) {
        try {
            Optional<Video> optionalVideo = videoDAO.findById(videoId);
            optionalVideo.map(video -> {
                video.setStatusCd(Constant.VIDEO_STATUS_DELETE);
                return videoDAO.save(video);
            }).orElseThrow(() -> new BusinessException("删除视频失败，视频不存在！"));
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("删除视频失败", e);
        }
    }

    @Override
    public VideoUserRelDTO getVideoUserRel(Long userId, Long videoId) {
        int isSupport, isOppose, isCollected;
        try {
            // 是否点赞
            VideoSupport videoSupport = videoSupportDAO.findByUserIdAndVideoId(userId, videoId);
            isSupport = videoSupport == null ? 0 : 1;
            // 是否不喜欢
            VideoOppose videoOppose = videoOpposeDAO.findByUserIdAndVideoId(userId, videoId);
            isOppose = videoOppose == null ? 0 : 1;
            // 是否收藏
            List<VideoCollection> videoCollections = videoCollectionDAO.findByUserIdAndVideoId(userId, videoId);
            isCollected = CollectionUtils.isEmpty(videoCollections) ? 0 : 1;
            VideoUserRelDTO videoUserRelDTO = new VideoUserRelDTO();
            videoUserRelDTO.setIsSupport(isSupport);
            videoUserRelDTO.setIsOppose(isOppose);
            videoUserRelDTO.setIsCollected(isCollected);
            return videoUserRelDTO;
        } catch (Exception e) {
            throw new BusinessException("查询用户视频关联信息失败", e);
        }
    }

    @Override
    public PageObject<List<VideoInCollectionDTO>> getVideoInFavorite(Long userId, Long favoriteId, Integer pageNumber, Integer pageSize) {
        PageObject<List<VideoInCollectionDTO>> pageObject;
        try {
            pageObject = new PageObject<>();
            Page<Object[]> videoInFavorite = videoCollectionDAO.findVideoInFavorite(userId, favoriteId, PageRequest.of(pageNumber, pageSize));
            if(CollectionUtils.isEmpty(videoInFavorite.getContent())) {
                return pageObject;
            }
            List<VideoInCollectionDTO> videoInCollectionDTOS = ObjectUtil.transArrayToClass(videoInFavorite.getContent(), VideoInCollectionDTO.class);
            pageObject.setContent(videoInCollectionDTOS);
            pageObject.setTotalPage(videoInFavorite.getTotalPages());
            pageObject.setCurrentPage(videoInFavorite.getNumber());
            pageObject.setTotalElements(videoInFavorite.getTotalElements());
            return pageObject;
        } catch (Exception e) {
            throw new BusinessException("查询收藏夹内视频失败", e);
        }
    }

}
