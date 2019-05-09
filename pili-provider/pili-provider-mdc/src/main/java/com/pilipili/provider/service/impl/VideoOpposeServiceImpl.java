package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.VideoOpposeDAO;
import com.pilipili.provider.entity.VideoOppose;
import com.pilipili.provider.service.VideoOpposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 描述： 视频不喜欢业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/19　15:34
 */
@Service
public class VideoOpposeServiceImpl implements VideoOpposeService {
    
    @Autowired
    private VideoOpposeDAO videoOpposeDAO;

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public Integer update(VideoOppose videoOppose) {
        try {
            if ((videoOppose.getUserId() == null) || (videoOppose.getVideoId() == null)) {
                throw new BusinessException("修改不喜欢状态失败");
            }
            VideoOppose queryVideoOppose = videoOpposeDAO.findByUserIdAndVideoId(videoOppose.getUserId(), videoOppose.getVideoId());
            if (queryVideoOppose != null) {
                videoOpposeDAO.deleteById(queryVideoOppose.getId());
                return 0;
            }
            videoOpposeDAO.save(videoOppose);
            return 1;
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("修改不喜欢状态失败", e);
        }
    }
}
