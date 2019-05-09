package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.service.FileService;
import com.pilipili.provider.util.Constant;
import com.pilipili.provider.util.SaveFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;

/**
 * 描述： 文件业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/13　23:58
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.static-path.media.video}")
    private String videoPath;


    @Override
    public String saveVideo(MultipartFile file) {
        try {
            return SaveFileUtil.saveVideo(uploadPath,videoPath, Constant.VIDEO_TYPE, file);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

}
