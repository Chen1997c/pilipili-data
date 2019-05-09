package com.pilipili.provider.feign.fallback;

import com.pilipili.provider.feign.AuthFeignService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述： auth远程服务调用熔断
 *
 * @author ChenJianChuan
 * @date 2019/4/26　17:00
 */
@Component
public class AuthFeignServiceImpl implements AuthFeignService {

    @Override
    public Map getToken(String username, String password, String grantType, String authorization) {
        Map<String,String> resultMap = new HashMap<>(2);
        resultMap.put("message", "auth服务不可用");
        return resultMap;
    }
}
