package com.tigerjay.eurekaclientconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.tigerjay.eurekaclientconsumer.rule.MyRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {
    @Bean
    public IRule getRule(){
        return new MyRule();
    }
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
