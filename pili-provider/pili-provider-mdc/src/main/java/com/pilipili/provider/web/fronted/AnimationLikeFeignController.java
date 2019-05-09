package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.PageObject;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.AnimationLikeDTO;
import com.pilipili.provider.entity.Animation;
import com.pilipili.provider.entity.AnimationLike;
import com.pilipili.provider.service.AnimationLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述： 番剧喜欢control
 *
 * @author ChenJianChuan
 * @date 2019/3/28　15:05
 */
@RestController
@RequestMapping("/animation")
public class AnimationLikeFeignController {

    @Autowired
    private AnimationLikeService animationLikeService;

    @GetMapping("/my-like")
    public ResultWrapper listLikeAnimations(@RequestParam Long userId,
                                            @RequestParam Integer pageNumber,
                                            @RequestParam Integer pageSize) {
        PageObject<List<AnimationLikeDTO>> listPageObject = animationLikeService.listUserLikeAnimations(userId, pageNumber, pageSize);
        return ResultWrapper.responseSuccess(listPageObject);
    }

    @PutMapping("/toggleLike")
    public ResultWrapper toggleLike(@RequestBody AnimationLike animationLike) {
        Integer result = animationLikeService.toggleLike(animationLike);
        return ResultWrapper.responseSuccess(result);
    }
}
