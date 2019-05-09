package com.pilipili.provider.service;

import com.pilipili.provider.entity.Channel;

import java.util.List;

/**
 * 描述：分区业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/14　16:04
 */
public interface ChannelService {

    /**
     * 获取全部分区及子分区
     * @return
     */
    List<Channel> listChannel();
}
