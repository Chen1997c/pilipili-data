package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.VideoSupportDAO;
import com.pilipili.provider.entity.VideoSupport;
import com.pilipili.provider.service.VideoSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 描述： video点赞业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/19　14:36
 */
@Service
public class VideoSupportServiceImpl implements VideoSupportService {

    @Autowired
    private VideoSupportDAO videoSupportDAO;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public Integer update(VideoSupport videoSupport) {
        try {
            if (videoSupport.getUserId() == null || videoSupport.getVideoId() == null) {
                throw new BusinessException("修改点赞状态失败");
            }
            VideoSupport queryVideoSupport = videoSupportDAO.findByUserIdAndVideoId(videoSupport.getUserId(), videoSupport.getVideoId());
            if (queryVideoSupport == null) {
                videoSupportDAO.save(videoSupport);
                return 1;
            }
            videoSupportDAO.deleteById(queryVideoSupport.getId());
            return 0;
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("修改点赞状态失败", e);
        }
    }
}
