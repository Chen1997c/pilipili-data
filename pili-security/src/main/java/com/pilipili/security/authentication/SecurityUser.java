package com.pilipili.security.authentication;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * 描述： 登录用户实体
 *
 * @author ChenJianChuan
 * @date 2018/12/20　17:34
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像路径
     */
    private String avatarUrl;


    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别 1男 2女 其他 保密
     */
    private Integer sexCode;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 状态码(1 正常 0 不可用 -1 待删除)
     */
    private Integer statusCd;

    /**
     * 权限id列表
     */
    private Set<Long> privileges;

    SecurityUser(LoginUserDTO loginUserDTO) {
        this.id = loginUserDTO.getId();
        this.loginName = loginUserDTO.getLoginName();
        this.password = loginUserDTO.getPassword();
        this.nickName = loginUserDTO.getNickName();
        this.avatarUrl = loginUserDTO.getAvatarUrl();
        this.statusCd = loginUserDTO.getStatusCd();
        this.privileges = loginUserDTO.getPrivileges();
        this.sexCode = loginUserDTO.getSexCode();
        this.birthday = loginUserDTO.getBirthday();
        this.signature = loginUserDTO.getSignature();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = null;
        if(!CollectionUtils.isEmpty(privileges)) {
            authorities = new ArrayList<>();
            for (Long aLong : privileges) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(aLong));
                authorities.add(simpleGrantedAuthority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginName;
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
        return this.statusCd == 1;
    }
}
