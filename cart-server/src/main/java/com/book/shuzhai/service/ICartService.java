package com.book.shuzhai.service;

import com.book.shuzhai.entity.Cart;
import com.book.shuzhai.entity.Product;

import java.util.List;

public interface ICartService {

    int addCart(Cart cart);

    List<Cart> getCartByUserId(Long userId);

    Long getUserCartNum(Long userId);

    int updateCartQuantity(Long quantity,Long userId,Long productId);

    int updateCartQuantityById(Long id,Long quantity);

    int deleteCartById(Long id);

    int deleteAllCart(List<Long> list);

    Cart getCartById(Long id);

    List<Cart> getAllCart(List<Long> id);

    Product getProductById(Long id);
}
