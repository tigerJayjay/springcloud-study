package com.tigerjay.eurekaclientzuul.routelocators;

import com.tigerjay.eurekadatabase.service.IDynamicPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义动态路由
 */

public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulProperties zuulProperties;
    @Autowired
    private IDynamicPropertyService dynamicPropertyService;
    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath,properties);
        this.zuulProperties = properties;
    }
    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String,ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(dynamicPropertyService.getDynamicProperty());
        Map<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        routesMap.forEach((key,value)->{
            String path = key;
            //修复不规范的路径
            if(!path.startsWith("/")){
                path = "/"+path;
            }
            //如果配置的有prefix，为path加上前缀
            if(StringUtils.hasText(zuulProperties.getPrefix())){
                path = zuulProperties.getPrefix()+path;
                if(!path.startsWith("/")){
                    path = "/"+path;
                }
            }
            values.put(path,value);
        });
        return values;
    }
}
