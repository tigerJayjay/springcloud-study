package com.tigerjay.eurekadatabase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tigerjay.eurekadatabase.mapper")
public class EurekaDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDatabaseApplication.class, args);
    }

}
