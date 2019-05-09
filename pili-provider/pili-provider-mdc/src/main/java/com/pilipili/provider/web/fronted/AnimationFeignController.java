package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.PageObject;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.*;
import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.service.AnimationService;
import com.pilipili.provider.vo.AnimationIndexSortVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    public ResultWrapper listRecommendAnimation(@RequestParam Integer pageNumber,
                                                @RequestParam Integer pageSize) {
        Page<AnimationRecommendDTO> animations = animationService.listRecommendAnimation(pageNumber,pageSize);
        return ResultWrapper.responseSuccess(animations);
    }

    @GetMapping("/anime-recommend")
    public ResultWrapper listRandomThreeAnimation(@RequestParam Long userId) {
        List<AnimationRandomDTO> animations = animationService.listRandomThreeAnimation(userId);
        return ResultWrapper.responseSuccess(animations);
    }

    @GetMapping("/internal-recommend")
    public ResultWrapper listInternalAnimation(@RequestParam Long userId) {
        List<AnimationRandomDTO> animations = animationService.listInternalThreeAnimation(userId);
        return ResultWrapper.responseSuccess(animations);
    }

    @GetMapping("/anime-play")
    public ResultWrapper getAnimationPlayInfo(@RequestParam Long userId, @RequestParam Long animationId) {
        AnimationPlayDTO animationPlayDTO = animationService.getAnimationPlayInfo(userId, animationId);
        return ResultWrapper.responseSuccess(animationPlayDTO);
    }

    @GetMapping("/anime-unlike")
    public ResultWrapper listAnimationsUnlike(@RequestParam Long userId,
                                              @RequestParam Integer pageNumber,
                                              @RequestParam Integer pageSize) {
        PageObject<List<AnimationUnlikeDTO>> animations = animationService.listAnimationsUnlike(userId, pageNumber, pageSize);
        return ResultWrapper.responseSuccess(animations);
    }

    @GetMapping("/u-see")
    public ResultWrapper getUserSeeInfo(@RequestParam Long userId,
                                        @RequestParam Long animationId){
        AnimationUserSeeDTO animationUserSeeDTO = animationService.getUserSeeInfo(userId, animationId);
        return ResultWrapper.responseSuccess(animationUserSeeDTO);
    }

    @GetMapping("/index")
    public ResultWrapper indexAnimation(@RequestParam Integer sortType,
                                        @RequestParam Integer sortRule,
                                        @RequestParam(required = false) Integer typeRule,
                                        @RequestParam(required = false) Integer styleRule,
                                        @RequestParam(required = false) Integer statusRule,
                                        @RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        AnimationIndexSortVO animationIndexSortVO =
                new AnimationIndexSortVO(sortType,sortRule,typeRule,styleRule,statusRule,pageNumber,pageSize);
        PageObject<List<AnimationIndexDTO>> pageObject = animationService.indexAnimation(animationIndexSortVO);
        return ResultWrapper.responseSuccess(pageObject);
    }
}
