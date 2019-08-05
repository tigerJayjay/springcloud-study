package com.tigerjay.eurekaserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {
        @RequestMapping("/get")
        public String temp(){
            return "hello world";
        }
}
