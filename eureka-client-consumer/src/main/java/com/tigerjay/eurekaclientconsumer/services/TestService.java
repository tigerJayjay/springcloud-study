package com.tigerjay.eurekaclientconsumer.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.tigerjay.eurekaclientconsumer.interfaces.TokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class TestService {

    @Autowired
    private TokenClient tokenClient;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name=HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY,value = "THREAD"),
            @HystrixProperty(name=HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED,value = "false")
    },fallbackMethod = "fall")
    @CacheResult
    public String getToken(String id){
        //测试hystrix自定义策略不会对通过线程传递的request产生影响
        System.out.print(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("token"));
        return tokenClient.getToken();
    }

    private String fall(String id){
        return id;
    }


    @HystrixCollapser(batchMethod = "callapserBatch",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,collapserProperties = {
            @HystrixProperty(name=HystrixPropertiesManager.TIMER_DELAY_IN_MILLISECONDS,value = "1000")
    })
    public Future<String> callapser(String id){
        return null;
    }

    @HystrixCommand
    public List<String> callapserBatch(List<String> ids){
        List<String> ls = new ArrayList<>();
        for(String id:ids){
            ls.add(id);
        }
        return ls;
    }
}
