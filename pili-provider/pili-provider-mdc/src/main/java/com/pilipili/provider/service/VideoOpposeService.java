package com.pilipili.provider.service;

import com.pilipili.provider.entity.VideoOppose;

/**
 * 描述： 视频不喜欢业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/19　15:33
 */
public interface VideoOpposeService {

    /**
     * 不喜欢或取消不喜欢
     * @param videoOppose
     * @return
     */
    Integer update(VideoOppose videoOppose);
}
