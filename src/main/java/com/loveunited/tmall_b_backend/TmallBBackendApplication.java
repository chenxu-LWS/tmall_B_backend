package com.loveunited.tmall_b_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.loveunited.tmall_b_backend.mapper")
@ServletComponentScan(basePackages = "com.loveunited.tmall_b_backend")
public class TmallBBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallBBackendApplication.class, args);
    }

}
