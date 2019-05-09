package com.pilipili.provider.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * 描述： 缓存配置
 *
 * @author ChenJianChuan
 * @date 2019/4/25　11:42
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    private static final String CAHCHE_PREFIX = "mdc_query_cache";

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        final char sp = ':';
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(CAHCHE_PREFIX);
            sb.append(sp);
            sb.append(target.getClass().getSimpleName());
            sb.append(sp);
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(sp);
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 更改值的序列化方式，否则在Redis可视化软件中会显示乱码。默认为JdkSerializationRedisSerializer
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer());
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置序列化方式
                .serializeValuesWith(pair)
                // 设置过期时间
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(defaultCacheConfig).build();
    }
}
