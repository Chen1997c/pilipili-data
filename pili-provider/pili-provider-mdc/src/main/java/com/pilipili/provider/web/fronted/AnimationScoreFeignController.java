package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.PageObject;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.AnimationScoreDTO;
import com.pilipili.provider.entity.AnimationScore;
import com.pilipili.provider.service.AnimationScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：番剧评分control
 *
 * @author ChenJianChuan
 * @date 2019/4/4　15:18
 */
@RestController
@RequestMapping("/animation")
public class AnimationScoreFeignController {

    @Autowired
    private AnimationScoreService animationScoreService;

    @GetMapping("/score")
    public ResultWrapper listAnimationScores(@RequestParam Long animationId,
                                             @RequestParam Integer pageNumber,
                                             @RequestParam Integer pageSize) {
        PageObject<List<AnimationScoreDTO>> pageObject = animationScoreService.listAnimationScores(animationId, pageNumber, pageSize);
        return ResultWrapper.responseSuccess(pageObject);
    }

    @PutMapping("/score")
    public ResultWrapper score(@RequestBody AnimationScore animationScore) {
        animationScoreService.score(animationScore);
        return ResultWrapper.responseSuccess();
    }

    @DeleteMapping("/score")
    public ResultWrapper deleteScore(@RequestParam Long userId,
                                     @RequestParam Long animationId) {
        animationScoreService.deleteScore(userId, animationId);
        return ResultWrapper.responseSuccess();
    }

}
