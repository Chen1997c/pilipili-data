package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.VideoOppose;
import com.pilipili.provider.entity.VideoSupport;
import com.pilipili.provider.service.VideoOpposeService;
import com.pilipili.provider.service.VideoSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 视频不喜欢control
 *
 * @author ChenJianChuan
 * @date 2019/4/19　15:31
 */
@RestController
@RequestMapping("/videoOppose")
public class VideoOpposeFeignController {

    @Autowired
    private VideoOpposeService videoOpposeService;

    @PutMapping("")
    public ResultWrapper update(@RequestBody VideoOppose VideoOppose) {
        Integer result = videoOpposeService.update(VideoOppose);
        String message = "";
        if(result == 1) {
            message = "不喜欢成功";
        } else if(result == 0) {
            message = "取消不喜欢成功";
        }
        return ResultWrapper.responseSuccess(message, result);
    }
}
