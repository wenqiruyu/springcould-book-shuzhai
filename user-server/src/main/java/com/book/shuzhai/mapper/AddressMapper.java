package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Address;

import java.util.List;

public interface AddressMapper {

    List<Address> selectUserAddress(Long userId);

    int insertUserAddress(Address address);

    Long selectUserAddressStatus(Long userId);

    int updateUserAddress(Address address);

    int deleteUserAddress(Long id);
}
