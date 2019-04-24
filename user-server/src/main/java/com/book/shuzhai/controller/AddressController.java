package com.book.shuzhai.controller;

import com.book.shuzhai.entity.Address;
import com.book.shuzhai.service.IAddressService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户收货地址user-server")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @GetMapping("/user/address/{id}")
    @CrossOrigin
    @ApiOperation(value = "获取收货地址", notes = "根据用户id查询该该用户下的可用地址")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    public ServerResponse<List<Address>> getUserAddress(@PathVariable("id")Long userId){
        List<Address> userAddress = addressService.getUserAddress(userId);
        if(userAddress == null){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createBySuccess(userAddress);
    }

    @PostMapping("/user/address")
    @CrossOrigin
    @ApiOperation(value = "添加收货地址", notes = "根据用户填写的地址信息添加用户的收货地址")
    @ApiImplicitParam(name = "address", value = "地址信息address", required = true, dataType = "Address")
    public ServerResponse<String> saveUserAddress(@RequestBody Address address){
        if(address.getStatus() == 0){
            // 查询用户是否存在默认地址
            Long addressId = addressService.getUserAddressStatus(address.getUserId());
            if(addressId != null && addressId != 0){
                addressService.updateUserAddress(new Address(addressId, 1));
            }
        }
        address.setCreateTime(new Date());
        address.setUpdateTime(new Date());
        int result = addressService.saveUserAddress(address);
        if(result == 1){
            return ServerResponse.createBySuccessMessage("添加收货地址成功");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @PutMapping("/user/address")
    @CrossOrigin
    @ApiOperation(value = "更改用户默认地址", notes = "修改用户选择的默认地址")
    @ApiImplicitParam(name = "address", value = "地址信息address", required = true, dataType = "Address")
    public ServerResponse<String> updateUserAddressStatus(@RequestBody Address address){
        // 查询用户是否存在默认地址
        Long addressId = addressService.getUserAddressStatus(address.getUserId());
        if(addressId == null || addressId == 0){
            // 用户没有设置默认地址
            int result = addressService.updateUserAddress(address);
            if(result == 1){
                return ServerResponse.createBySuccessMessage("默认地址设置成功");
            }
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        // 存在默认值，将默认地址修改
        int updateResult = addressService.updateUserAddress(new Address(addressId, 1));
        if(updateResult == 1){
            int i = addressService.updateUserAddress(address);
            if(i == 1){
                return ServerResponse.createBySuccessMessage("默认地址设置成功");
            }
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @PutMapping("/user/addressDesc")
    @CrossOrigin
    @ApiOperation(value = "用户地址信息更改", notes = "修改用户选择地址的信息")
    @ApiImplicitParam(name = "address", value = "地址信息address", required = true, dataType = "Address")
    public ServerResponse<String> updateUserAddress(@RequestBody Address address){
        address.setUpdateTime(new Date());
        System.out.println(address);
        int result = addressService.updateUserAddress(address);
        if(result == 1){
            return ServerResponse.createBySuccessMessage("成功修改收货地址");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }

    @DeleteMapping("/user/address/{id}")
    @CrossOrigin
    @ApiOperation(value = "添加收货地址", notes = "根据用户填写的地址信息添加用户的收货地址")
    @ApiImplicitParam(name = "address", value = "地址信息address", required = true, dataType = "Address")
    public ServerResponse<String> deleteUserAddress(@PathVariable Long id){
        int result = addressService.deleteUserAddress(id);
        if(result == 1){
            return ServerResponse.createBySuccessMessage("成功删除收货地址");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
    }
}
