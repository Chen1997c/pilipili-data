package com.pilipili.provider.web.fronted;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.dto.AnimationRandomDTO;
import com.pilipili.provider.dto.AnimationRecommendDTO;
import com.pilipili.provider.entity.AnimationDetails;
import com.pilipili.provider.service.AnimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：番剧control
 *
 * @author ChenJianChuan
 * @date 2019/3/26　20:11
 */
@RestController
@RequestMapping("/animation")
public class AnimationFeignController {

    @Autowired
    private AnimationService animationService;

    @GetMapping("/editor-recommend")
    public ResultWrapper listRecommendAnimation() {
        List<AnimationRecommendDTO> animations = animationService.listRecommendAnimation();
        return ResultWrapper.responseSuccess(animations);
    }

    @GetMapping("/anime-recommend")
    public ResultWrapper listRandomThreeAnimation() {
        List<AnimationRandomDTO> animations = animationService.listRandomThreeAnimation();
        return ResultWrapper.responseSuccess(animations);
    }

    @GetMapping("/internal-recommend")
    public ResultWrapper listInternalAnimation() {
        List<AnimationRandomDTO> animations = animationService.listInternalThreeAnimation();
        return ResultWrapper.responseSuccess(animations);
    }
}
