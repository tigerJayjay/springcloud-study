package com.tigerjay.eurekaclientconsumer.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.tigerjay.eurekaclientconsumer.interfaces.FileClient;
import com.tigerjay.eurekaclientconsumer.interfaces.StoreClient;
import com.tigerjay.eurekaclientconsumer.interfaces.TokenClient;
import com.tigerjay.eurekaclientconsumer.services.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Api
@RestController
public class TempController {
    @Autowired
    private StoreClient storeClient;
    @Autowired
    private FileClient fileClient;
    @Autowired
    private TokenClient tokenClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestService testService;
    @GetMapping("/consumerGet")
    @CacheResult
    public String temp(String a){
        return restTemplate.getForEntity("http://producer/get",String.class,a).getBody();
    }

    @GetMapping("/getStore")
    public String getStore(){
        String s = storeClient.getStores();
        return s;
    }
    @GetMapping("/update")
    public int update(){
        return storeClient.update(1,"1");
    }

    //参数名必须与feign接口方法参数名一样
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(MultipartFile file){
       return fileClient.fileUpload1(file);
    }

    @ApiOperation(value="测试token获取")

    @PostMapping("/getTokenC")
    public String getToken(String a){
       /*继承方式实现hystrix缓存
        BadRequestException request = new BadRequestException(restTemplate,"1");
        request.execute();
        System.out.print(request.isResponseFromCache());
        BadRequestException request1 = new BadRequestException(restTemplate,"1");
        request1.execute();
        System.out.print(request1.isResponseFromCache());*/
        String res = testService.getToken(a);
        testService.getToken(a);
        return res;
    }
    @ApiOperation("测试hystrix降级")
    @HystrixCommand(fallbackMethod = "defaultBack")
    @GetMapping("/testHystrix")
    public String testHystrix(String a){
        throw new RuntimeException(a);
    }

    public String defaultBack(String a,Throwable b){
        b.printStackTrace();
        return "Hystrix降级返回"+a;
    }
    @GetMapping("/testCollapser")
    public String testCallapser(String a){
        Future<String> f = testService.callapser(a);
        String res = null;
        try {
            res = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return res;
    }

}
