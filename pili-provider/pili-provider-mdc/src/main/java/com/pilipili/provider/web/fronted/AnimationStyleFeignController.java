package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.AnimationStyle;
import com.pilipili.provider.entity.AnimationStyleRel;
import com.pilipili.provider.service.AnimationStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：番剧风格control
 *
 * @author ChenJianChuan
 * @date 2019/4/4　16:15
 */
@RestController
@RequestMapping("/animation")
public class AnimationStyleFeignController {

    @Autowired
    private AnimationStyleService animationStyleService;

    @GetMapping("/style")
    public ResultWrapper listAnimationStyle(@RequestParam Long animationId) {
        List<AnimationStyleRel> animationStyleRelList = animationStyleService.listAnimationStyle(animationId);
        return ResultWrapper.responseSuccess(animationStyleRelList);
    }

    @GetMapping("/style/all")
    public ResultWrapper listAnimationStyleAll() {
        List<AnimationStyle> animationStyleRelList = animationStyleService.listAllAnimationStyle();
        return ResultWrapper.responseSuccess(animationStyleRelList);
    }
}
