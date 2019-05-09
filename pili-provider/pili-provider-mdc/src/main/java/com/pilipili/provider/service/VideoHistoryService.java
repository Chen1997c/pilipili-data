package com.pilipili.provider.service;

/**
 * 描述： 视频历史业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/16　23:08
 */
public interface VideoHistoryService {

    /**
     * 更新或保存视频观看记录
     * @param userId
     * @param videoId
     */
    void updateHistory(Long userId, Long videoId);
}
