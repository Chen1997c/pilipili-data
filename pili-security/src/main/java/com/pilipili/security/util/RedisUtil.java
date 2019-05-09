package com.pilipili.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 描述： redis操作工具类
 *
 * @author ChenJianChuan
 * @date 2019/4/25　9:40
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * key前缀拼接
     * @param key
     * @param prefix
     * @return
     */
    public String getKey(String key, String prefix) {
        return prefix + key;
    }

    /**
     * 增
     * @param key
     * @param value
     */
    public void setValue(String key, String value, Long timeout) {
        stringRedisTemplate.opsForValue().set(key,value, timeout, TimeUnit.MINUTES);
    }

    /**
     * 删
     * @param key
     */
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 查
     * @param key
     * @return
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
