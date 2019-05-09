package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.VideoSupport;
import com.pilipili.provider.service.VideoSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：视频点赞control
 *
 * @author ChenJianChuan
 * @date 2019/4/16　19:43
 */
@RestController
@RequestMapping("/videoSupport")
public class VideoSupportFeignController {

    @Autowired
    private VideoSupportService videoSupportService;

    @PutMapping("")
    public ResultWrapper update(@RequestBody VideoSupport videoSupport) {
        Integer result = videoSupportService.update(videoSupport);
        String message = "";
        if(result == 1) {
            message = "点赞成功";
        } else if(result == 0) {
            message = "取消点赞成功";
        }
        return ResultWrapper.responseSuccess(message, result);
    }
}
