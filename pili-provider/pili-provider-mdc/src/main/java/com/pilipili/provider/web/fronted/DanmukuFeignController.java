package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.entity.Danmuku;
import com.pilipili.provider.service.DanmukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述： 弹幕control
 *
 * @author ChenJianChuan
 * @date 2019/4/21　14:53
 */
@RestController
public class DanmukuFeignController {

    @Autowired
    private DanmukuService danmukuService;

    @GetMapping("/danmuku/{refId}")
    public List<Danmuku> getVideoDanmuku(@PathVariable String refId) {
        return danmukuService.findByRefId(refId);
    }

    @PostMapping("/danmuku")
    public ResultWrapper sendDanmuku(@RequestBody Danmuku danmuku) {
        danmukuService.send(danmuku);
        return ResultWrapper.responseSuccess();
    }
}
