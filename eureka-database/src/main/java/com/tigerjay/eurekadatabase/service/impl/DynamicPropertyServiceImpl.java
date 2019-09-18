package com.tigerjay.eurekadatabase.service.impl;

import com.tigerjay.eurekadatabase.entity.DynamicProperty;
import com.tigerjay.eurekadatabase.mapper.DynamicPropertyMapper;
import com.tigerjay.eurekadatabase.service.IDynamicPropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DynamicPropertyServiceImpl implements IDynamicPropertyService {
    @Autowired
    private  DynamicPropertyMapper dynamicPropertyMapper;

    @Override
    public Map<String, ZuulProperties.ZuulRoute> getDynamicProperty() {
        Map<String, ZuulProperties.ZuulRoute> zuulRouteMap = new LinkedHashMap<>();
        Map<String,Object> param = new HashMap<>();
        param.put("enabled",1);
        List<DynamicProperty> dynamicProperties  = dynamicPropertyMapper.selectByMap(param);
        dynamicProperties.forEach(entity->{
            if(StringUtils.isEmpty(entity.getPath())) return;
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            BeanUtils.copyProperties(entity,zuulRoute);
            zuulRouteMap.put(entity.getPath(),zuulRoute);
        });
        return zuulRouteMap;
    }
}
