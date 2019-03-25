package com.book.shuzhai.service.impl;

import com.book.shuzhai.entity.Cart;
import com.book.shuzhai.mapper.CartMapper;
import com.book.shuzhai.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public int addCart(Cart cart) {
        if (cart != null) {
            // 查询用的购物车
            List<Cart> list = cartMapper.queryCartByUserId(cart.getUserId());
            boolean flag = false;
            Long bookNum = null;
            for (Cart c : list) {
                if (c.getProductId() == cart.getProductId()) {
                    flag = true;
                    bookNum = c.getQuantity();
                }
            }
            // 如果该用户加入过相同的图书进购物车中
            if (flag) {
                return cartMapper.updateCartQuantity(cart.getQuantity() + bookNum, cart.getUserId(), cart.getProductId());
            } else {
                return cartMapper.insertCart(cart);
            }
        }
        return -1;
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        return cartMapper.queryCartByUserId(userId);
    }

    @Override
    public Long getUserCartNum(Long userId) {
        return cartMapper.queryUserCartNum(userId);
    }

    @Override
    public int updateCartQuantity(Long quantity, Long userId,Long productId) {
        return cartMapper.updateCartQuantity(quantity,userId,productId);
    }

    @Override
    public int updateCartQuantityById(Long id, Long quantity) {
        return cartMapper.updateCartQuantityById(id,quantity);
    }

    @Override
    public int deleteCartById(Long id) {
        return cartMapper.deleteCartById(id);
    }

    @Override
    public int deleteAllCart(List<Long> list) {
        return cartMapper.deleteAllCart(list);
    }

    @Override
    public Cart getCartById(Long id) {
        return cartMapper.queryCartById(id);
    }
}
