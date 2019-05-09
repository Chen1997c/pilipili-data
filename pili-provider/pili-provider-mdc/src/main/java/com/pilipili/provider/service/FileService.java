package com.pilipili.provider.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 描述： 文件业务接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
public interface FileService {

    /**
     * 保存视频
     * @param file
     * @return
     */
    String saveVideo(MultipartFile file);
}
