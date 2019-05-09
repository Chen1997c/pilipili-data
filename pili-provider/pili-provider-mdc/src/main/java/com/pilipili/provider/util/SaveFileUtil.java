package com.pilipili.provider.util;

import com.pilipili.common.exception.ResolveDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * 描述： 保存文件工具类
 *
 * @author ChenJianChuan
 * @date 2019/4/14　21:03
 */
@Slf4j
public class SaveFileUtil {

    /**
     * 年
     */
    private static int year;
    /**
     * 月
     */
    private static int month;
    /**
     * 日
     */
    private static int day;

    static {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DATE);
    }


    public static String saveVideo(String uploadPath, String path, String type, MultipartFile file) {
        try {
            long fileSize = file.getSize();
            String fileType = file.getOriginalFilename().substring(Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf("."));
            String originFinalName = file.getName();
            log.info("文件保存===> 原文件名为:" + originFinalName);
            log.info("文件保存===> 文件大小为为:" + fileSize);
            log.info("文件保存===> 文件名类型为:" + originFinalName);
            if (type.lastIndexOf(fileType.toLowerCase()) == -1) {
                throw new ResolveDataException("不支持的文件类型:" + fileType);
            }
            String newFileName = System.currentTimeMillis() + generateUUID() + fileType;
            path += year + "/" + month + "/" + day;
            String folderPath = uploadPath + path;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File saveFile = new File(folder, newFileName);
            file.transferTo(saveFile);
            log.info("文件保存成功===》保存文件夹为:{}, 保存文件名为:{}", folderPath, newFileName);
            return path  + "/" + newFileName;
        } catch (ResolveDataException e) {
            throw new ResolveDataException(e.getMessage());
        } catch (Exception e) {
            throw new ResolveDataException("文件保存失败", e);
        }
    }

    /**
     * 生成uuid
     * @return
     */
    private static String generateUUID() {
        return String.valueOf(UUID.randomUUID()).replace("-", "");
    }
}
