package com.pilipili.provider.web.fronted;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述： 用户control
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:07
 */
@RestController
@RequestMapping("/user")
public class RegisterFeignController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/isExist")
    public ResultWrapper isLoginNameUsed(String loginName) {
        Integer result = registerService.checkLoginNameIsUsed(loginName);
        if (result == 1) {
            return ResultWrapper.responseSuccess("用户名可用");
        }
        return ResultWrapper.responseFail("用户名已被使用");
    }

    @PostMapping("/register")
    public ResultWrapper register(@RequestBody User user) {
        registerService.register(user);
        return ResultWrapper.responseSuccess("注册成功");
    }

    @GetMapping("/emailCode")
    public ResultWrapper emailValidateCode(String email) {
        String emailCode =  registerService.generateEmailCode(email);
        return ResultWrapper.responseSuccess(emailCode);
    }
}
