package com.book.shuzhai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("pay-server")
public interface PayFeignClient {

    @GetMapping("/ali/pay")
    String toPay(@RequestParam("orderId")String orderId, @RequestParam("money")String money,
                 @RequestParam("productName")String name, @RequestParam("quantity")String quantity);
}
