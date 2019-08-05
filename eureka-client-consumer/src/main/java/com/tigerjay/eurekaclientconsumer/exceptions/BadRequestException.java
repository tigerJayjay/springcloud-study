package com.tigerjay.eurekaclientconsumer.exceptions;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class BadRequestException extends HystrixCommand {
    private RestTemplate restTemplate;
    private String id;
    public BadRequestException(RestTemplate restTemplate,String id){
        super(HystrixCommandGroupKey.Factory.asKey("springCloudCacheGroup"));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    @Override
    protected Object run() throws Exception {
       String res =  restTemplate.getForObject("http://producer/get",String.class,id);
       return res;
    }

   public void clearCache(){
       HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey(
               "springCloudCacheGroup"
       ),HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(1));

   }


    @Override
    protected Object getFallback() {
        return super.getFallback();
    }
}
