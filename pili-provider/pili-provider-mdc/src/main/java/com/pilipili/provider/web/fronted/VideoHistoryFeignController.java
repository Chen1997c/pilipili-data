package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.service.VideoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 视频历史control
 *
 * @author ChenJianChuan
 * @date 2019/4/18　0:23
 */
@RestController
@RequestMapping("/videoHistory")
public class VideoHistoryFeignController {

    @Autowired
    private VideoHistoryService videoHistoryService;

    @PutMapping("")
    public ResultWrapper updateHistory(@RequestParam Long userId,
                                       @RequestParam Long videoId) {
        videoHistoryService.updateHistory(userId, videoId);
        return ResultWrapper.responseSuccess();
    }
}
