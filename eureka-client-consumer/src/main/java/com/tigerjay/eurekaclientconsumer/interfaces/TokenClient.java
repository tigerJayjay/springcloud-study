package com.tigerjay.eurekaclientconsumer.interfaces;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "producer")
public interface TokenClient {

    @PostMapping(value="/getToken")
    public String getToken();
}
