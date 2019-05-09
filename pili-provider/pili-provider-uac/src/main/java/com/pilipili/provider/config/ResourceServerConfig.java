package com.pilipili.provider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 描述： 资源配置
 *
 * @author ChenJianChuan
 * @date 2018/1/11 10:8
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**", "/user/register", "/user/emailCode", "/loginUser")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
