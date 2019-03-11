package com.pilipili.security.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 描述： 登录用户实体
 *
 * @author ChenJianChuan
 * @date 2018/12/20　17:34
 */
@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private LoginUserDTO loginUserDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = null;
        if(this.loginUserDTO != null) {
            authorities = new ArrayList<>();
            for (Long aLong : loginUserDTO.getPrivileges()) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(aLong));
                authorities.add(simpleGrantedAuthority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.loginUserDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return this.loginUserDTO.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getLoginUserDTO().getStatusCd() == 1;
    }
}
