package com.pilipili.provider.service;

import com.pilipili.provider.entity.VideoSupport;

/**
 * 描述： video点赞业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
public interface VideoSupportService {

    /**
     * 点赞或取消点赞
     * @param videoSupport
     * @return
     */
    Integer update(VideoSupport videoSupport);
}
