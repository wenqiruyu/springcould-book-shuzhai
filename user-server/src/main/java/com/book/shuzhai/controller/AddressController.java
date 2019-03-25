package com.book.shuzhai.controller;

import com.book.shuzhai.entity.Address;
import com.book.shuzhai.service.IAddressService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "用户收货地址user-server")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/user/address/{id}")
    @CrossOrigin
    @ApiOperation(value = "收货地址", notes = "根据用户id查询该该用户下的可用地址")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    public ServerResponse<List<Address>> getUserAddress(@PathVariable("id")Long userId){
        List<Address> userAddress = addressService.getUserAddress(userId);
        if(userAddress == null){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createBySuccess(userAddress);
    }
}
