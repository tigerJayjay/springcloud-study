package com.tigerjay.eurekaclientzuul.routelocators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义动态路由
 */

public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    @Autowired
    private ZuulProperties zuulProperties;
    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath,properties);
    }
    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String,ZuulProperties.ZuulRoute> routesMap = new HashMap<>();
        routesMap.putAll(super.locateRoutes());
        return super.locateRoutes();
    }
}
