package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述： 文件control
 *
 * @author ChenJianChuan
 * @date 2019/4/13　23:55
 */
@RestController
@RequestMapping("/upload")
public class FileFeignController {

    @Autowired
    private FileService fileService;

    @PostMapping("/video")
    public ResultWrapper uploadVideo(@RequestParam("file") MultipartFile multipartFile) {
        String savePath = fileService.saveVideo(multipartFile);
        return ResultWrapper.responseSuccess(savePath);
    }
}
