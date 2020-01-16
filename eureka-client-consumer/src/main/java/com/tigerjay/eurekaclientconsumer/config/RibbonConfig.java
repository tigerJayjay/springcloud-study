package com.tigerjay.eurekaclientconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.tigerjay.eurekaclientconsumer.rule.MyRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix ="ribbon.http")
@PropertySource("classpath:application.properties")
public class RibbonConfig {

    private String user;
    private String pass;
    @Bean
    public IRule getRule(){
        return new MyRule();
    }
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        //设置ribbon调用eureka服务时的用户名和密码
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(user,pass));
        return restTemplate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
