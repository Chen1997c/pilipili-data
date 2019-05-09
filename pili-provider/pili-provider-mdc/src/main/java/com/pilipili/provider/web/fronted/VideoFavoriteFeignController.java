package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.VideoFavoriteDTO;
import com.pilipili.provider.entity.VideoFavorite;
import com.pilipili.provider.service.VideoFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述： 视频收藏夹control
 *
 * @author ChenJianChuan
 * @date 2019/4/16　23:08
 */
@RestController
@RequestMapping("/videoFavorite")
public class VideoFavoriteFeignController {

    @Autowired
    private VideoFavoriteService videoFavoriteService;

    @GetMapping("/create")
    public ResultWrapper getCreateByUserId(Long userId) {
        List<VideoFavoriteDTO> videoFavoriteDTOList = videoFavoriteService.getCreateByUserId(userId);
        return ResultWrapper.responseSuccess(videoFavoriteDTOList);
    }

    @GetMapping("/collect")
    public ResultWrapper getCollectByUserId(Long userId) {
        List<VideoFavoriteDTO> videoFavoriteDTOList = videoFavoriteService.getCollectByUserId(userId);
        return ResultWrapper.responseSuccess(videoFavoriteDTOList);
    }

    @PutMapping("")
    public ResultWrapper update(@RequestBody VideoFavorite videoFavorite) {
        videoFavoriteService.update(videoFavorite);
        return ResultWrapper.responseSuccess();
    }

    @DeleteMapping("")
    public ResultWrapper delete(@RequestParam Long id) {
        videoFavoriteService.delete(id);
        return ResultWrapper.responseSuccess();
    }

    @PostMapping("")
    public ResultWrapper add(@RequestBody VideoFavorite videoFavorite) {
        videoFavoriteService.add(videoFavorite);
        return ResultWrapper.responseSuccess();
    }

    @PutMapping("/change")
    public ResultWrapper changeFavorite(@RequestParam Long userId,
                                        @RequestParam Long favoriteId) {
        Integer result = videoFavoriteService.changeFavorite(userId, favoriteId);
        String resultMessage;
        if(result == 1) {
            resultMessage = "收藏成功";
        } else {
            resultMessage = "取消收藏成功";
        }
        return ResultWrapper.responseSuccess(resultMessage, result);
    }

}
