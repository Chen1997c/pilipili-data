package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.VideoCollectionDAO;
import com.pilipili.provider.entity.VideoCollection;
import com.pilipili.provider.service.VideoCollectionService;
import com.pilipili.provider.vo.VideoCollectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 视频收藏业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/17　23:25
 */
@Service
public class VideoCollectionServiceImpl implements VideoCollectionService {

    @Autowired
    private VideoCollectionDAO videoCollectionDAO;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public void deleteOne(Long userId, Long videoId, Long favoriteId) {
        try {
            videoCollectionDAO.deleteByUserIdAndVideoIdAndVideoFavoriteId(userId, videoId, favoriteId);
        } catch (Exception e) {
            throw new BusinessException("取消收藏失败", e);
        }
    }

    @Override
    public List<Long> getVideoIn(Long userId, Long videoId) {
        List<Long> favoriteIdList;
        try {
            favoriteIdList = videoCollectionDAO.findAllIdByUserIdAndVideoId(userId, videoId);
            if (CollectionUtils.isEmpty(favoriteIdList)) {
                favoriteIdList = new ArrayList<>();
            }
            return favoriteIdList;
        } catch (Exception e) {
            throw new BusinessException("查询视频所在收藏夹失败", e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public String addVideoCollection(VideoCollectionVO videoCollectionVO) {
        String message = "";
        try {
            List<VideoCollection> videoCollections = videoCollectionDAO.findByUserIdAndVideoId(videoCollectionVO.getUserId(), videoCollectionVO.getVideoId());
            if (CollectionUtils.isEmpty(videoCollections)) {
                message = "收藏成功";
                if (CollectionUtils.isEmpty(videoCollectionVO.getFavoriteIdList())) {
                    return message;
                }
                saveVideoCollection(videoCollectionVO);
            } else {
                videoCollections.forEach(videoCollection -> {
                    videoCollectionDAO.deleteById(videoCollection.getId());
                });
                if (CollectionUtils.isEmpty(videoCollectionVO.getFavoriteIdList())) {
                    message = "取消收藏成功";
                } else {
                    saveVideoCollection(videoCollectionVO);
                }
            }
            return message;
        } catch (Exception e) {
            throw new BusinessException("更新时视频收藏失败", e);
        }
    }

    private void saveVideoCollection(VideoCollectionVO videoCollectionVO) {
        videoCollectionVO.getFavoriteIdList().forEach(id -> {
            VideoCollection videoCollection = new VideoCollection();
            videoCollection.setUserId(videoCollectionVO.getUserId());
            videoCollection.setVideoId(videoCollectionVO.getVideoId());
            videoCollection.setVideoFavoriteId(id);
            videoCollectionDAO.save(videoCollection);
        });
    }
}
