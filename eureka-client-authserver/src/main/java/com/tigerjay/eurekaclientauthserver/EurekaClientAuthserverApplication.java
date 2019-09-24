package com.tigerjay.eurekaclientauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientAuthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientAuthserverApplication.class, args);
    }
}
