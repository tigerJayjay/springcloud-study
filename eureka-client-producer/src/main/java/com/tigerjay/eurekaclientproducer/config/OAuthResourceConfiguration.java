package com.tigerjay.eurekaclientproducer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class OAuthResourceConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("WRIGTH").tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests().antMatchers("/**").authenticated()
               .antMatchers(HttpMethod.POST,"/get")
               .hasAnyAuthority("WRIGTH_READ");
    }
}
