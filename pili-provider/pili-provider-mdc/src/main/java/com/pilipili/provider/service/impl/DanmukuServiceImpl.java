package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.DanmukuDAO;
import com.pilipili.provider.entity.Danmuku;
import com.pilipili.provider.service.DanmukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述： 弹幕业务实现
 *
 * @author ChenJianChuan
 * @date 2019/4/21　23:22
 */
@Service
public class DanmukuServiceImpl implements DanmukuService {

    @Autowired
    private DanmukuDAO danmukuDAO;

    @Override
    public void send(Danmuku danmuku) {
        try {
            danmuku.setCreateTime(new Date());
            danmukuDAO.save(danmuku);
        } catch (Exception e) {
            throw new BusinessException("保存弹幕失败", e);
        }
    }

    @Override
    public List<Danmuku> findByRefId(String refId) {
        try {
            return danmukuDAO.findByRefId(refId);
        } catch (Exception e) {
            throw new BusinessException("获取弹幕失败", e);
        }
    }
}
