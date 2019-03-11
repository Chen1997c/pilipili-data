package com.pilipili.security.config;

import com.pilipili.security.properties.AuthClientProperties;
import com.pilipili.security.properties.AuthSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * 描述： 授权配置
 *
 * @author ChenJianChuan
 * @date 2019/2/2　14:40
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(converter());
    }

    @Bean
    public JwtAccessTokenConverter converter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(AuthSecurityProperties.JWT_SIGN_KEY);
        return new JwtAccessTokenConverter();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess(AuthSecurityProperties.TOKEN_KEY_ACCESS_METHOD)
                .checkTokenAccess(AuthSecurityProperties.CHECK_TOKEN_ACCESS_METHOD);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .accessTokenConverter(converter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(AuthClientProperties.AUTH_CLIENT_ID)
                .scopes(AuthClientProperties.AUTH_CLIENT_SCOPE)
                .secret(passwordEncoder.encode(AuthClientProperties.AUTH_CLIENT_SECRET))
                .authorizedGrantTypes(AuthClientProperties.AUTH_TYPES)
                .accessTokenValiditySeconds(AuthClientProperties.AUTH_TOKEN_EXPIRE);
    }
}
