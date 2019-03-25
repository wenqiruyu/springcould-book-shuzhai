package com.book.shuzhai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.book.shuzhai.mapper")
public class SsmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmServerApplication.class, args);
    }

}
