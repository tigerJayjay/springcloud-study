package com.tigerjay.eurekaclientconsumer.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("producer")
public interface StoreClient {
    @RequestMapping(method= RequestMethod.POST,value = "/stores")
    String getStores();
    @RequestMapping(method = RequestMethod.POST,value = "/store",consumes = "application/json")
    int update(@RequestParam(value = "id") Integer id, @RequestParam(value = "content") String content);
}
