package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.Channel;
import com.pilipili.provider.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述： 分区control
 *
 * @author ChenJianChuan
 * @date 2019/4/14　16:02
 */
@RestController
public class ChannelFeignController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("/channel")
    public ResultWrapper listChannel() {
        List<Channel> channelList = channelService.listChannel();
        return ResultWrapper.responseSuccess(channelList);
    }
}
