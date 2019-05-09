package com.pilipili.security.authentication;

import com.pilipili.common.util.ObjectUtil;
import com.pilipili.common.response.ResultWrapper;
import com.pilipili.security.feign.LoginFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 描述： 认证业务实现
 *
 * @author ChenJianChuan
 * @date 2019/2/2　14:46
 */
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private LoginFeignService loginFeignService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        ResultWrapper result = loginFeignService.login(loginName);
        if(result.getData() != null) {
            LoginUserDTO loginUser = ObjectUtil.objectConvertToClass(result.getData(), LoginUserDTO.class);
            return new SecurityUser(loginUser);
        }
        return null;
    }
}
