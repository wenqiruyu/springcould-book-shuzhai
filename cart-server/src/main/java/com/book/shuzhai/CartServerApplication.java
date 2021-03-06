package com.book.shuzhai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.book.shuzhai.mapper")
@EnableFeignClients
public class CartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartServerApplication.class, args);
    }

}
