package com.pilipili.provider.web.fronted;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： 用户control
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:07
 */
@RestController
public class RegisterFeignController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/user/isExist")
    public ResultWrapper isLoginNameUsed(String loginName) {
        Integer result = registerService.checkLoginNameIsUsed(loginName);
        if (result == 1) {
            return ResultWrapper.responseSuccess("用户名可用");
        }
        return ResultWrapper.responseFail("用户名已被使用");
    }

    @PostMapping("/user/register")
    public ResultWrapper register(@RequestBody User user) {
        registerService.register(user);
        return ResultWrapper.responseSuccess("注册成功");
    }
}
