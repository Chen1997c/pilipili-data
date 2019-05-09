package com.pilipili.provider.service;

/**
 * 描述：番剧历史业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/28　15:01
 */
public interface AnimationHistoryService {

    /**
     * 更新或者保存观看历史
     * @param userId
     * @param resId
     */
    void updateHistory(Long userId, Long resId);
}
