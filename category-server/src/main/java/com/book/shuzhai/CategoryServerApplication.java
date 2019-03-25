package com.book.shuzhai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.book.shuzhai.mapper")
public class CategoryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServerApplication.class, args);
    }

}
