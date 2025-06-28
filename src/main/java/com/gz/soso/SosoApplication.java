package com.gz.soso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gz.soso.mapper")
public class SosoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SosoApplication.class, args);
    }

}
