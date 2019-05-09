package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.ChannelDAO;
import com.pilipili.provider.entity.Channel;
import com.pilipili.provider.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述： 分区业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/14　16:05
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;

    @Override
    public List<Channel> listChannel() {
        try {
            return channelDAO.findAll();
        }catch (Exception e) {
            throw new BusinessException("查询分区失败", e);
        }
    }
}
