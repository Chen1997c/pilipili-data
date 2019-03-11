package com.pilipili.security.web.endpoint;

import com.pilipili.common.util.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述： 注销API
 *
 * @author ChenJianChuan
 * @date 2019/3/7　19:23
 */
@FrameworkEndpoint
public class LogoutEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @DeleteMapping("/oauth/token")
    @ResponseBody
    public ResultWrapper logout(@RequestParam("access_token") String accessToken) {
        if(consumerTokenServices.revokeToken(accessToken)) {
            return ResultWrapper.responseSuccess("注销成功");
        }
        return ResultWrapper.responseSuccess("注销失败");
    }
}
