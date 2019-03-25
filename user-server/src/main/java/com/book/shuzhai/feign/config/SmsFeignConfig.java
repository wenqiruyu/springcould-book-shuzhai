package com.book.shuzhai.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class SmsFeignConfig {

    @Bean
    public Logger.Level logger() {
        // 将feign日志级别开启为FULL级别  依次有  NONE（关闭日志） BASIC  HEADERS  FULL
        return Logger.Level.FULL;
    }
}
