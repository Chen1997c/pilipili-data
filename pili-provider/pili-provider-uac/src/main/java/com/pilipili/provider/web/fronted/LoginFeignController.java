package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.LoginUserDTO;
import com.pilipili.provider.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 登录control
 *
 * @author ChenJianChuan
 * @date 2019/3/5　9:49
 */
@RestController
public class LoginFeignController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/loginUser")
    public ResultWrapper login(String loginName) {
        LoginUserDTO loginUser = loginService.login(loginName);
        return ResultWrapper.responseSuccess(loginUser);
    }
}
