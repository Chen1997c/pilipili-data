package com.pilipili.provider.feign;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.feign.fallback.UserFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述： user远程服务调用接口
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:57
 */
@FeignClient(name = "pili-provider-uac", fallback = UserFeignServiceImpl.class)
public interface UserFeignService {

    /**
     * 根据用户id获取用户信息实体
     * @param userId
     * @return
     */
    @GetMapping("/user")
    ResultWrapper getUser(@RequestParam Long userId);
}
