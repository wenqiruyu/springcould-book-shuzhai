package com.book.shuzhai.feign;

import com.book.shuzhai.entity.Product;
import com.book.shuzhai.utils.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("product-server")
public interface ProductFeignClient {

    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    ServerResponse<Product> getProductById(@PathVariable("id") Long id);
}
