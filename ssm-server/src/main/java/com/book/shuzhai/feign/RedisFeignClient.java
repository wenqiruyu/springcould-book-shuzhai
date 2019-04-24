package com.book.shuzhai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@FeignClient("redis-server")
public interface RedisFeignClient {

    /**
     * 将string类型的redis设置时效为15分钟
     * @param stringKey
     * @param stringValue
     */
    @RequestMapping(value = "/redis/string", method = RequestMethod.POST)
    void toSaveString(@RequestParam("stringKey") String stringKey,@RequestParam("time") Long time,@RequestParam("stringValue") String stringValue);

    @RequestMapping(value = "/redis/string", method = RequestMethod.GET)
    String toGetString(@RequestParam String stringKey);
}
