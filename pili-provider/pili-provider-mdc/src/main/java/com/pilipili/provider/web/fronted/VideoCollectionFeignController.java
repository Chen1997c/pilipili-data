package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.service.VideoCollectionService;
import com.pilipili.provider.vo.VideoCollectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述： 视频收藏control
 *
 * @author ChenJianChuan
 * @date 2019/4/17　23:24
 */
@RestController
@RequestMapping("/videoCollection")
public class VideoCollectionFeignController {

    @Autowired
    private VideoCollectionService videoCollectionService;

    @DeleteMapping("")
    public ResultWrapper deleteOne(@RequestParam Long userId,
                                   @RequestParam Long videoId,
                                   @RequestParam Long favoriteId) {
        videoCollectionService.deleteOne(userId, videoId, favoriteId);
        return ResultWrapper.responseSuccess();
    }

    @GetMapping("/videoIn")
    public ResultWrapper getVideoIn(@RequestParam Long userId,
                                    @RequestParam Long videoId) {
        List<Long> favoriteIdList = videoCollectionService.getVideoIn(userId, videoId);
        return ResultWrapper.responseSuccess(favoriteIdList);
    }

    @PostMapping("")
    public ResultWrapper addVideoCollection(@RequestBody VideoCollectionVO videoCollectionVO) {
        String message = videoCollectionService.addVideoCollection(videoCollectionVO);
        return ResultWrapper.responseSuccess(message);
    }
}
