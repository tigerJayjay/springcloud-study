package com.tigerjay.eurekaclientzuul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication(scanBasePackages = "com.tigerjay")
@EnableDiscoveryClient
@EnableZuulProxy
@EnableOAuth2Sso
@MapperScan({"com.tigerjay.eurekadatabase.mapper"})
public class EurekaClientZuulApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientZuulApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().disable()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and().authorizeRequests()
                .anyRequest().authenticated();//其他的路径需要认证后的用户可以访问
    }
}
