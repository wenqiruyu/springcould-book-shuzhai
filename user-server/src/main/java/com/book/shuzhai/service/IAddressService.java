package com.book.shuzhai.service;

import com.book.shuzhai.entity.Address;

import java.util.List;

public interface IAddressService {

    List<Address> getUserAddress(Long userId);

    int saveUserAddress(Address address);

    Long getUserAddressStatus(Long userId);

    int updateUserAddress(Address address);

    int deleteUserAddress(Long id);
}
