package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.PageObject;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.UserLikeDTO;
import com.pilipili.provider.entity.UserLike;
import com.pilipili.provider.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述： 用户关注control
 *
 * @author ChenJianChuan
 * @date 2019/4/19　17:32
 */
@RestController
@RequestMapping("/userLike")
public class UserLikeFeignController {

    @Autowired
    private UserLikeService userLikeService;

    @GetMapping("/myLike")
    public ResultWrapper listMyLike(@RequestParam Long userId,
                                    @RequestParam Integer pageNumber,
                                    @RequestParam Integer pageSize) {
        PageObject<List<UserLikeDTO>> userLikeList = userLikeService.listMyLike(userId,pageNumber,pageSize);
        return ResultWrapper.responseSuccess(userLikeList);
    }

    @GetMapping("/likeMe")
    public ResultWrapper listLikeMe(@RequestParam Long userId,
                                    @RequestParam Integer pageNumber,
                                    @RequestParam Integer pageSize) {
        PageObject<List<UserLikeDTO>> userLikeList = userLikeService.listLikeMe(userId,pageNumber,pageSize);
        return ResultWrapper.responseSuccess(userLikeList);
    }

    @PutMapping("")
    public ResultWrapper changeLike(@RequestBody UserLike userLike) {
        Integer result = userLikeService.changeLike(userLike);
        String message;
        if(result == 0) {
            message = "取消关注成功";
        } else {
            message = "关注成功";
        }
        return ResultWrapper.responseSuccess(message, result);
    }
}
