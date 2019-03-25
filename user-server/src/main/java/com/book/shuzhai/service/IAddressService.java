package com.book.shuzhai.service;

import com.book.shuzhai.entity.Address;

import java.util.List;

public interface IAddressService {

    List<Address> getUserAddress(Long userId);
}
