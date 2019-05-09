package com.pilipili.security.feign.fallback;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.security.feign.LoginFeignService;
import org.springframework.stereotype.Component;

/**
 * 描述：login fallback
 *
 * @author ChenJianChuan
 * @date 2019/3/6　14:38
 */
@Component
public class LoginFeignServiceImpl implements LoginFeignService {

    @Override
    public ResultWrapper login(String loginName) {
        return ResultWrapper.responseFail("uac服务不可用");
    }
}
