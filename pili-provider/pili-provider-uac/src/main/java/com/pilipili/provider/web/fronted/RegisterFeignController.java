package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.service.RegisterService;
import com.pilipili.provider.vo.RegisterVO;
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
    public ResultWrapper register(@RequestBody RegisterVO registerVO) {
        Integer result = registerService.register(registerVO);
        if (result == 0) {
            return ResultWrapper.responseFail("邮箱验证码错误");
        } else {
            return ResultWrapper.responseSuccess("注册成功", result);
        }
    }

    @GetMapping("/emailCode")
    public ResultWrapper emailValidateCode(@RequestParam String email) {
        Integer result = registerService.generateEmailCode(email);
        if (result == 0) {
            return ResultWrapper.responseFail("该邮箱已被使用");
        }
        return ResultWrapper.responseSuccess("验证码发送成功", null);
    }
}
