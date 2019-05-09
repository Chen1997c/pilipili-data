package com.pilipili.provider.service;

import com.pilipili.provider.entity.Danmuku;

import java.util.List;

/**
 * 描述： 弹幕业务接口
 *
 * @author ChenJianChuan
 * @date 2019/4/1　20:09
 */
public interface DanmukuService {

    /**
     * 发射弹幕
     * @param danmuku
     */
    void send(Danmuku danmuku);

    /**
     * 找弹幕
     * @param refId
     * @return
     */
    List<Danmuku> findByRefId(String refId);
}
