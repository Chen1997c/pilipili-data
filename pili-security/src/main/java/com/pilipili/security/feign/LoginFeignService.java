package com.pilipili.security.feign;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.security.feign.fallback.LoginFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述： login feign client
 *
 * @author ChenJianChuan
 * @date 2019/3/6　14:33
 */
@FeignClient(name = "pili-provider-uac", fallback = LoginFeignServiceImpl.class)
public interface LoginFeignService {

    /**
     * 登陆查询服务调用
     * @param loginName
     * @return
     */
    @GetMapping("/loginUser")
    ResultWrapper login(@RequestParam String loginName);
}
