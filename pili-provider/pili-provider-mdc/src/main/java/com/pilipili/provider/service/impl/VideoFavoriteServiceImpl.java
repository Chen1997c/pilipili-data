package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.common.util.ObjectUtil;
import com.pilipili.provider.dao.VideoCollectionDAO;
import com.pilipili.provider.dao.VideoFavoriteCollectionDAO;
import com.pilipili.provider.dao.VideoFavoriteDAO;
import com.pilipili.provider.dto.VideoFavoriteDTO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.VideoFavorite;
import com.pilipili.provider.entity.VideoFavoriteCollection;
import com.pilipili.provider.feign.UserFeignService;
import com.pilipili.provider.service.VideoFavoriteService;
import com.pilipili.provider.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 视频收藏夹业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/16　23:09
 */
@Service
public class VideoFavoriteServiceImpl implements VideoFavoriteService {

    @Autowired
    private VideoFavoriteDAO videoFavoriteDAO;
    @Autowired
    private VideoCollectionDAO videoCollectionDAO;
    @Autowired
    private VideoFavoriteCollectionDAO videoFavoriteCollectionDAO;
    @Autowired
    private UserFeignService userFeignService;

    @Override
    public List<VideoFavoriteDTO> getCreateByUserId(Long userId) {
        List<VideoFavoriteDTO> videoFavoriteDTOS = new ArrayList<>();
        try {
            // 查询默认收藏夹
            String defaultCoverUrl = videoCollectionDAO.getCoverUrlByFavoriteId(userId, Constant.DEFAULT_FAVORITE_ID);
            Long defaultVideoCounts = videoCollectionDAO.countAllByUserIdAndVideoFavoriteId(userId, Constant.DEFAULT_FAVORITE_ID);
            VideoFavoriteDTO defaultVideoFavoriteDTO = new VideoFavoriteDTO();
            defaultVideoFavoriteDTO.setId(Constant.DEFAULT_FAVORITE_ID);
            defaultVideoFavoriteDTO.setCoverUrl(defaultCoverUrl);
            defaultVideoFavoriteDTO.setIsPublic(Constant.PUBLIC_CODE);
            defaultVideoFavoriteDTO.setName(Constant.DEFAULT_FAVORITE_NAME);
            defaultVideoFavoriteDTO.setVideoCounts(defaultVideoCounts);
            videoFavoriteDTOS.add(defaultVideoFavoriteDTO);
            List<VideoFavorite> videoFavorites = videoFavoriteDAO.findAllByUserId(userId);
            if (CollectionUtils.isEmpty(videoFavorites)) {
                return videoFavoriteDTOS;
            }
            videoFavorites.forEach(videoFavorite -> {
                VideoFavoriteDTO videoFavoriteDTO = setVideoFavoriteDTO(userId, videoFavorite);
                videoFavoriteDTOS.add(videoFavoriteDTO);
            });
            return videoFavoriteDTOS;
        } catch (Exception e) {
            throw new BusinessException("查询自创视频收藏夹失败", e);
        }
    }

    @Override
    public List<VideoFavoriteDTO> getCollectByUserId(Long userId) {
        List<VideoFavoriteDTO> videoFavoriteDTOS = new ArrayList<>();
        try {
            List<VideoFavoriteCollection> videoFavoriteCollections = videoFavoriteCollectionDAO.findAllByUserId(userId);
            if (CollectionUtils.isEmpty(videoFavoriteCollections)) {
                return videoFavoriteDTOS;
            }
            videoFavoriteCollections.forEach(videoFavoriteCollection -> {
                VideoFavorite videoFavorite = videoFavoriteDAO.getPulicById(videoFavoriteCollection.getVideoFavoriteId());
                if (videoFavorite != null) {
                    VideoFavoriteDTO videoFavoriteDTO = setVideoFavoriteDTO(videoFavorite.getUserId(), videoFavorite);
                    ResultWrapper resultWrapper = userFeignService.getUser(videoFavorite.getUserId());
                    if (resultWrapper.getData() != null) {
                        User user = ObjectUtil.objectConvertToClass(resultWrapper.getData(), User.class);
                        videoFavoriteDTO.setUser(user);
                    }
                    videoFavoriteDTOS.add(videoFavoriteDTO);
                }
            });
            return videoFavoriteDTOS;
        } catch (Exception e) {
            throw new BusinessException("查询收藏视频收藏夹失败", e);
        }
    }

    @Override
    public void update(VideoFavorite videoFavorite) {
        try {
            if (videoFavorite.getId() != null) {
                VideoFavorite queryVideoFavorite = videoFavoriteDAO.findById(videoFavorite.getId())
                        .orElseThrow(() -> new BusinessException("更新收藏视频收藏夹信息失败,收藏夹不存在"));
                queryVideoFavorite.setName(videoFavorite.getName());
                queryVideoFavorite.setProfiles(videoFavorite.getProfiles());
                queryVideoFavorite.setIsPublic(videoFavorite.getIsPublic());
                videoFavoriteDAO.save(queryVideoFavorite);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("更新收藏视频收藏夹信息失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void delete(Long id) {
        try {
            // 将视频从收藏夹移除
            videoCollectionDAO.deleteAllByVideoFavoriteId(id);
            // 删除对收藏夹的收藏
            videoFavoriteCollectionDAO.deleteByVideoFavoriteId(id);
            // 删除收藏夹
            videoFavoriteDAO.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException("删除收藏视频收藏夹失败", e);
        }
    }

    @Override
    public Integer changeFavorite(Long userId, Long favoriteId) {
        int result = 0;
        try {
            VideoFavoriteCollection videoFavoriteCollection = videoFavoriteCollectionDAO.findByUserIdAndVideoFavoriteId(userId, favoriteId);
            if(videoFavoriteCollection != null) {
                videoFavoriteCollectionDAO.deleteById(videoFavoriteCollection.getId());
            } else {
                videoFavoriteCollection = new VideoFavoriteCollection();
                videoFavoriteCollection.setUserId(userId);
                videoFavoriteCollection.setVideoFavoriteId(favoriteId);
                videoFavoriteCollectionDAO.save(videoFavoriteCollection);
                result = 1;
            }
            return result;
        } catch (Exception e) {
            throw new BusinessException("切换收藏收藏夹失败", e);
        }
    }

    @Override
    public void add(VideoFavorite videoFavorite) {
        try {
            videoFavoriteDAO.save(videoFavorite);
        }catch (Exception e) {
            throw new BusinessException("新增收藏夹失败", e);
        }
    }

    private VideoFavoriteDTO setVideoFavoriteDTO(Long userId, VideoFavorite videoFavorite) {
        String coverUrl = videoCollectionDAO.getCoverUrlByFavoriteId(userId, videoFavorite.getId());
        Long videoCounts = videoCollectionDAO.countAllByUserIdAndVideoFavoriteId(userId, videoFavorite.getId());
        VideoFavoriteDTO videoFavoriteDTO = new VideoFavoriteDTO();
        videoFavoriteDTO.setId(videoFavorite.getId());
        videoFavoriteDTO.setCoverUrl(coverUrl);
        videoFavoriteDTO.setIsPublic(videoFavorite.getIsPublic());
        videoFavoriteDTO.setName(videoFavorite.getName());
        videoFavoriteDTO.setVideoCounts(videoCounts);
        videoFavoriteDTO.setProfiles(videoFavorite.getProfiles());
        return videoFavoriteDTO;
    }
}
