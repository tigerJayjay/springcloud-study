package com.tigerjay.eurekaclientconsumer.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient("producer")
public interface FileClient {
    //与方法名无关
    @RequestMapping(method = RequestMethod.POST,value = "/uploadFile/server",
                    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
                    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String fileUpload1(@RequestPart MultipartFile file);
}
