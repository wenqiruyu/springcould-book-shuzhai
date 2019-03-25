package com.book.shuzhai.mapper;

import com.book.shuzhai.entity.Cart;

import java.util.List;

public interface CartMapper {

    int insertCart(Cart cart);

    List<Cart> queryCartByUserId(Long userId);

    Long queryUserCartNum(Long userId);

    int updateCartQuantity(Long quantity,Long userId,Long productId);

    int updateCartQuantityById(Long id,Long quantity);

    int deleteCartById(Long id);

    int deleteAllCart(List<Long> list);

    Cart queryCartById(Long id);
}
