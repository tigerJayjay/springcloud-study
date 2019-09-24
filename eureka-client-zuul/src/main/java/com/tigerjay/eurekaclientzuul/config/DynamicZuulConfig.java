package com.tigerjay.eurekaclientzuul.config;

import com.tigerjay.eurekaclientzuul.routelocators.DynamicRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicZuulConfig {
    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private ZuulProperties zuulProperties;

    @Bean
    public DynamicRouteLocator setProperty(){
        DynamicRouteLocator routeLocator = new DynamicRouteLocator(serverProperties.getServlet().getServletPrefix(),zuulProperties);
        return routeLocator;
    }
}
