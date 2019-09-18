package com.tigerjay.eurekadatabase.service;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.Map;

public interface IDynamicPropertyService {
    Map<String, ZuulProperties.ZuulRoute> getDynamicProperty();
}
