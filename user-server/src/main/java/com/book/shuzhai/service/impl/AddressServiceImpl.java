package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.Address;
import com.book.shuzhai.mapper.AddressMapper;
import com.book.shuzhai.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getUserAddress(Long userId) {
        return addressMapper.selectUserAddress(userId);
    }
}
