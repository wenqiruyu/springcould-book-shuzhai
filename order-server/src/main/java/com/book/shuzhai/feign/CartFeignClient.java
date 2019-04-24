package com.book.shuzhai.feign;

import com.book.shuzhai.entity.Product;
import com.book.shuzhai.utils.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("cart-server")
public interface CartFeignClient {

    @DeleteMapping("/cart")
    String deleteAllCart(@RequestParam String allId);

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    ServerResponse getAllCart(@RequestParam String allId);

    @RequestMapping(value = "/cart/product", method = RequestMethod.GET)
    List<Product> getAllCartProduct(@RequestParam String allId);

    @RequestMapping(value = "/cart/product/{id}", method = RequestMethod.GET)
    Product getProductById(@PathVariable("id") Long id);
}
