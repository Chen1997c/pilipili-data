package com.pilipili.security.feign.impl;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.security.feign.LoginFeignClient;
import org.springframework.stereotype.Component;

/**
 * 描述：login fallback
 *
 * @author ChenJianChuan
 * @date 2019/3/6　14:38
 */
@Component
public class LoginFeignClientImpl implements LoginFeignClient {

    @Override
    public ResultWrapper login(String loginName) {
        return ResultWrapper.responseFail("uac服务不可用");
    }
}
