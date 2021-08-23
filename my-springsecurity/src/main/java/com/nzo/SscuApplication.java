package com.nzo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nzo.dao")
public class SscuApplication {
    public static void main(String[] args) {
        SpringApplication.run(SscuApplication.class, args);
    }
}
