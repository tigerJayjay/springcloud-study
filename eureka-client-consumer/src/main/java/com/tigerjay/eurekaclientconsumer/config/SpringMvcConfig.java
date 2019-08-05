package com.tigerjay.eurekaclientconsumer.config;

import com.tigerjay.eurekaclientconsumer.interceptors.HystrixContextInteceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HystrixContextInteceptor());
    }
}
