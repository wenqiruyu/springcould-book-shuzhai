package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Address;

import java.util.List;

public interface AddressMapper {

    List<Address> selectUserAddress(Long userId);
}
