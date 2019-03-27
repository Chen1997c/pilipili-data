package com.pilipili.provider.feign.fallback;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.feign.UserFeignService;
import org.springframework.stereotype.Component;

/**
 * 描述： user远程调用fallback
 *
 * @author ChenJianChuan
 * @date 2019/3/26　9:17
 */
@Component
public class UserFeignServiceImpl implements UserFeignService {

    @Override
    public ResultWrapper getUser(Long userId) {
        return ResultWrapper.responseFail("uac服务不可用");
    }

}
