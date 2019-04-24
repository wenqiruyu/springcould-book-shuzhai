package com.book.shuzhai.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.book.shuzhai.entity.Order;
import com.book.shuzhai.entity.Product;
import com.book.shuzhai.feign.CartFeignClient;
import com.book.shuzhai.feign.PayFeignClient;
import com.book.shuzhai.feign.ProductFeignClient;
import com.book.shuzhai.service.IOrderService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private PayFeignClient payFeignClient;
    
    @PostMapping("/order")
    @CrossOrigin
    @ApiOperation(value="添加订单", notes="添加订单并进行支付")
    @ApiImplicitParam(name = "order", value = "订单详情order", required = true, dataType = "Order")
    public ServerResponse<String> saveOrder(@RequestBody Order order){
        String orderNumber = UUID.randomUUID().toString().replaceAll("-", "");
        order.setOrderNumber(orderNumber);
        order.setCreateTime(new Date());
        // 设置订单为未支付状态
        order.setStatus(1);
        int result = orderService.addOrder(order);
        if(result == 1){
            System.out.println(order.getCartId() == null);
            System.out.println(StringUtils.isBlank(order.getCartId()));
            if(order.getCartId() == null){
                // 购物车删除成功，进行对订单的支付
                String payResult = payFeignClient.toPay(orderNumber, order.getOrderMoney().toString(), "订单流水号：" + orderNumber, "1");
                return ServerResponse.createBySuccessMessage(payResult);
            }
            // 添加订单成功,进行删除购物车
            String deResult = cartFeignClient.deleteAllCart(order.getCartId());
            JSONObject jsonObject = JSON.parseObject(deResult);
            if("1".equals(jsonObject.getString("status"))){
                // 购物车删除成功，进行对订单的支付
                String payResult = payFeignClient.toPay(orderNumber, order.getOrderMoney().toString(), "订单流水号：" + orderNumber, "1");
                return ServerResponse.createBySuccessMessage(payResult);
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @GetMapping("/order")
    @CrossOrigin
    @ApiOperation(value="订单列表", notes="获取全部订单")
    public ServerResponse<List<Order>> getAllOrder(){
        List<Order> allOrder = orderService.getAllOrder();
        if(allOrder == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(allOrder);
    }

    @GetMapping("/order/group/{userId}")
    @CrossOrigin
    @ApiOperation(value="按日期分组查询", notes="按日期分组查询")
    @ApiImplicitParam(name = "userId", value = "用户id userId", required = true, dataType = "String")
    public ServerResponse<List<Order>> getAllOrderGroup(@PathVariable("userId") String userId){
        List<Order> allOrder = orderService.getAllOrderGroup(Long.parseLong(userId));
        for(Order order:allOrder){
            if(order.getCartId() != null){
                // 获取购物车集合，进行批量查询
                List<Product> allCartProduct = cartFeignClient.getAllCartProduct(order.getCartId());
                order.setProduct(allCartProduct);
            }else{
                ArrayList<Product> pro = new ArrayList<>();
                Product productById = cartFeignClient.getProductById(order.getProductId());
                productById.setQuantity(order.getQuantity());
                pro.add(productById);
                order.setProduct(pro);
            }
        }
        if(allOrder == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(allOrder);
    }
}
