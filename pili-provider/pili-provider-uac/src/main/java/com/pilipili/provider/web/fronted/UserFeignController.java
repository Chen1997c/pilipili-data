package com.pilipili.provider.web.fronted;

import com.pilipili.common.util.ResultWrapper;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述： user control
 *
 * @author ChenJianChuan
 * @date 2019/3/26　9:05
 */
@RestController
public class UserFeignController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResultWrapper getUser(Long userId) {
        User user = userService.getUserById(userId);
        return ResultWrapper.responseSuccess(user);
    }
}
