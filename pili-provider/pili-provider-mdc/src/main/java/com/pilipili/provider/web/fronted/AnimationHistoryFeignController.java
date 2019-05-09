package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.service.AnimationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 番剧历史control
 *
 * @author ChenJianChuan
 * @date 2019/4/10　9:53
 */
@RestController
@RequestMapping("/animation")
public class AnimationHistoryFeignController {

    @Autowired
    private AnimationHistoryService animationHistoryService;

    @PutMapping("/history")
    public ResultWrapper updateHistory(@RequestParam Long userId, @RequestParam Long resId) {
        animationHistoryService.updateHistory(userId, resId);
        return ResultWrapper.responseSuccess();
    }
}
