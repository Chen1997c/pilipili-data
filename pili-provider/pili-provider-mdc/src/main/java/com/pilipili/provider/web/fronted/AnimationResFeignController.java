package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.AnimationRes;
import com.pilipili.provider.service.AnimationResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：番剧资源control
 *
 * @author ChenJianChuan
 * @date 2019/3/29　15:52
 */
@RestController
@RequestMapping("/animation")
public class AnimationResFeignController {

    @Autowired
    private AnimationResService animationResService;

    @GetMapping("/anime-res")
    public ResultWrapper listAnimationRes(@RequestParam Long animationId) {
        List<AnimationRes> animationResList = animationResService.listAllByAnimationId(animationId);
        return ResultWrapper.responseSuccess(animationResList);
    }
}
