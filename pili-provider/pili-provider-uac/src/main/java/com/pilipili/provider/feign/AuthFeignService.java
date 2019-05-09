package com.pilipili.provider.feign;

import com.pilipili.provider.feign.fallback.AuthFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 描述： auth远程服务调用接口
 *
 * @author ChenJianChuan
 * @date 2019/4/26　16:58
 */
@FeignClient(value = "pili-security", fallback = AuthFeignServiceImpl.class)
public interface AuthFeignService {

    /**
     * 调用认证中心获取token
     * @param username
     * @param password
     * @param grantType
     * @param authorization
     * @return
     */
    @PostMapping("/oauth/token")
    Map getToken(@RequestParam String username,
                 @RequestParam String password,
                 @RequestParam(value = "grant_type") String grantType,
                 @RequestHeader String authorization);
}
