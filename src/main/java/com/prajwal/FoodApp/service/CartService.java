package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Cart;
import com.prajwal.FoodApp.model.CartItem;

import java.util.List;

public interface CartService {

    public Cart getCart(Long userId);

    public  CartItem addItem(Long foodId,Long userId) throws Exception;

    public void removeItem(Long foodId,Long userId) throws Exception;

    public void clearCart(Long userId) ;

    void removeCartItemById(Long cartItemId, Long userId) throws Exception;
}
