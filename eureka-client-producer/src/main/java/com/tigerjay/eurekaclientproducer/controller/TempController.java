package com.tigerjay.eurekaclientproducer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
public class TempController {
    private final Logger logger = LoggerFactory.getLogger(TempController.class);
    @Autowired
    private Environment environment;
    @Autowired
    private DiscoveryClient client;
    @RequestMapping("/get")
    public String temp(){
        ServiceInstance localServiceInstance = client.getInstances("producer").get(0);
        logger.info(localServiceInstance.getHost()+":"+localServiceInstance.getPort()+":"+localServiceInstance.getServiceId());
        return environment.getProperty("server.port");
    }
    @RequestMapping("/stores")
    public String getStore(){
        return "stores";
    }
    @RequestMapping("/store")
    public int update(Integer id,String content){
        logger.info(id+":"+content);
        return 1;
    }

    @PostMapping(value = "/uploadFile/server",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String fileUpload( MultipartFile file){
        return file.getOriginalFilename();
    }

    @PostMapping("/getToken")
    public String getToken(HttpServletRequest request){
        return request.getHeader("token");
    }
}
