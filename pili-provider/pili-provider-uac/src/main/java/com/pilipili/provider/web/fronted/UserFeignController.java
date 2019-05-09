package com.pilipili.provider.web.fronted;

import com.pilipili.common.response.ResultWrapper;
import com.pilipili.provider.dto.LoginUserDTO;
import com.pilipili.provider.dto.UserLikeInfoDTO;
import com.pilipili.provider.dto.UserRoleDTO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserRoleRel;
import com.pilipili.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/user")
    public ResultWrapper updateUser(@RequestBody LoginUserDTO user) {
        userService.updateUser(user);
        return ResultWrapper.responseSuccess();
    }

    @GetMapping("/user/likeInfo")
    public ResultWrapper userLikeInfo(@RequestParam Long userId,
                                      @RequestParam Long currentUserId) {
        UserLikeInfoDTO userLikeInfoDTO = userService.getUserLikeInfo(userId,currentUserId);
        return ResultWrapper.responseSuccess(userLikeInfoDTO);
    }

    @GetMapping("/users")
    public ResultWrapper queryUserList(@RequestParam Integer pageNumber,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(required = false) Integer statusCd,
                                       @RequestParam(required = false) String nickName,
                                       @RequestParam(required = false) Long roleId ) {
        Page<UserRoleRel> userRoleRelList = userService.queryUserList(pageNumber, pageSize, statusCd, nickName, roleId);
        return ResultWrapper.responseSuccess(userRoleRelList);
    }

    @PutMapping("/user/status")
    public ResultWrapper updateStatus(@RequestParam Long id,
                                      @RequestParam Integer statusCd) {
        userService.updateStatus(id, statusCd);
        return ResultWrapper.responseSuccess();
    }

    @PostMapping("/user")
    public ResultWrapper addUser(@RequestBody UserRoleDTO userRoleDTO) {
        UserRoleDTO savedUserRoleDTO = userService.addUser(userRoleDTO);
        return ResultWrapper.responseSuccess(savedUserRoleDTO);
    }


}
