package com.book.shuzhai.controller;

import com.book.shuzhai.entity.Cart;
import com.book.shuzhai.service.ICartService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "购物车服务cart-server")
public class CartController {

    @Autowired
    private ICartService cartService;

    /**
     * 添加购物车，当用户添加的商品在数据库中有相同用户的商品时，只进行商品书本加1
     * @param map
     * @return
     */
    @PostMapping("/cart")
    @CrossOrigin
    @ApiOperation(value = "添加购物车",notes = "根据用户操作添加用户购物车")
    @ApiImplicitParam(name = "map",value = "购物车信息集合map",required = true,dataType = "Map")
    public ServerResponse<String> addCart(@RequestBody Map<String,String> map){
        System.out.println(map.get("userId"));
        int result
                = cartService.addCart(new Cart(Long.parseLong(map.get("userId")), Long.parseLong(map.get("bookId")),
                Long.parseLong(map.get("bookNum")), 0, new Date(), new Date()));
        if(result == 1){
            return ServerResponse.createBySuccessMessage("商品成功添加到购物车");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @GetMapping("/cart/{userId}")
    @CrossOrigin
    @ApiOperation(value = "用户购物车详情",notes = "根据用户id查询用户购物车详情")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String")
    public ServerResponse<List<Cart>> getUserCart(@PathVariable String userId){
        List<Cart> cartByUserId = cartService.getCartByUserId(Long.parseLong(userId));
        if(cartByUserId == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(cartByUserId);
    }

    @GetMapping("/cart/sum/{userId}")
    @CrossOrigin
    @ApiOperation(value = "用户购物车数",notes = "根据用户id查询用户的购物车数")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String")
    public ServerResponse getUserCartSum(@PathVariable String userId){
        Long userCartNum = cartService.getUserCartNum(Long.parseLong(userId));
        return ServerResponse.createBySuccess(userCartNum);
    }

    @PutMapping("/cart")
    @CrossOrigin
    @ApiOperation(value = "用户购物车商品数量",notes = "根据购物车id，修改购物车中的商品数量")
    @ApiImplicitParam(name = "map",value = "购物车信息集合map",required = true,dataType = "Map")
    public ServerResponse<String> updateCartQuantity(@RequestBody Map<String,String> map){
        int result = cartService.updateCartQuantityById(Long.parseLong(map.get("id")),Long.parseLong(map.get("quantity")));
        if(result != 1){
            return ServerResponse.createBySuccessMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccessMessage("成功修改购物车商品数量");
    }

    @DeleteMapping("/cart/{id}")
    @CrossOrigin
    @ApiOperation(value = "用户删除购物车商品",notes = "根据购物车id，删除购物车中的商品")
    @ApiImplicitParam(name = "id",value = "购物车id",required = true,dataType = "Long")
    public ServerResponse<String> deleteCartById(@PathVariable Long id){
        int result = cartService.deleteCartById(id);
        if(result != 1){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccessMessage("成功删除商品");
    }
    
    @DeleteMapping("/cart")
    @CrossOrigin
    @ApiOperation(value = "用户进行批量删除购物车",notes = "根据购物车id集合，使用','进行拼接字符串，删除购物车中的商品")
    @ApiImplicitParam(name = "allId",value = "购物车id集合",required = true,dataType = "String")
    public ServerResponse<String> deleteAllCart(@RequestParam String allId){
        String[] split = allId.split(",");
        // List<String> list = Arrays.asList(split);
        List<Long> list = new ArrayList<>();
        for(String str : split){
            list.add(Long.parseLong(str));
        }
        int result = cartService.deleteAllCart(list);
        if(result < 1){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createByErrorMessage("成功删除商品");
    }
}
