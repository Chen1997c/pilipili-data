package com.pilipili.provider.dao;


import com.pilipili.provider.entity.Danmuku;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 描述： danmuku dao
 *
 * @author ChenJianChuan
 * @date 2019/3/25　17:17
 */
public interface DanmukuDAO extends MongoRepository<Danmuku,String> {

    /**
     * 找弹幕
     * @param refId
     * @return
     */
    List<Danmuku> findByRefId(String refId);
}
