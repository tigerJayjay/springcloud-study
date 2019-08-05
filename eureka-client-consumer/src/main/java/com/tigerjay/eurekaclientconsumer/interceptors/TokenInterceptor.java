package com.tigerjay.eurekaclientconsumer.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenInterceptor implements RequestInterceptor{
    @Override
    public void apply(RequestTemplate requestTemplate) {
       HttpServletRequest request =  this.getHttpServletRequest();
       if(request == null){
           return;
       }
       String token = request.getHeader("token");
       requestTemplate.header("token",token);
    }

    private HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
