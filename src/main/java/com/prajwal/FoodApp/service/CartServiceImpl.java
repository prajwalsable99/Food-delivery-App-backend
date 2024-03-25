package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Cart;
import com.prajwal.FoodApp.model.CartItem;
import com.prajwal.FoodApp.model.Food;
import com.prajwal.FoodApp.repository.CartItemRepository;
import com.prajwal.FoodApp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{


    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    FoodService foodService;
    @Override
    public Cart getCart(Long userId) {
       Cart cart= cartRepository.findByCustomer_Id(userId);
        long total = calculateTotal(cart);
        cart.setTotal(total);
        return cartRepository.save(cart);


    }

    private long calculateTotal(Cart cart) {
        long total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        return total;
    }
    @Override
    public CartItem addItem(Long foodId,Long userId) throws Exception {

        Food food=foodService.findFoodById(foodId);

        Cart cart=getCart(userId);

        Optional<CartItem> isAlready=cartItemRepository.findByCartIdAndFoodId(cart.getId(),food.getId());
        if(isAlready.isPresent()){
            CartItem cartItem=isAlready.get();
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItem.setTotalPrice(cartItem.getTotalPrice() + food.getPrice());
            return cartItemRepository.save(cartItem);
        }else{

            CartItem cartItem=new CartItem();
            cartItem.setCart(cart);
            cartItem.setFood(food);
            cartItem.setQuantity(1L);
            cartItem.setTotalPrice(food.getPrice());
            return cartItemRepository.save(cartItem);
        }




    }

    @Override
    public void removeItem(Long foodId, Long userId) throws Exception {
        Food food = foodService.findFoodById(foodId);
        Cart cart = getCart(userId);

        // Find and remove the matching CartItem from the cart's items list
        boolean found = false;
        Iterator<CartItem> iterator = cart.getItems().iterator();
        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            if (cartItem.getFood().getId().equals(foodId)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        // If the item was not found in the cart, throw an exception
        if (!found) {
            throw new Exception("Item not found in cart");
        }



        // Save the updated cart
        cartRepository.save(cart);
    }

    @Override

    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByCustomer_Id(userId);
        if (cart != null && !cart.getItems().isEmpty()) {
            cart.getItems().clear();
            cart.setTotal(0L);
            cartRepository.save(cart);
        }
    }

    @Override
    public void removeCartItemById(Long cartItemId, Long userId) throws Exception {
        // Find the cart item by its ID
        Cart cart = getCart(userId);
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);

        // If the cart item exists, remove it from the cart and save the cart
        optionalCartItem.ifPresent(cartItem -> {

            cart.getItems().remove(cartItem);
            cartRepository.save(cart);
        });

        if (optionalCartItem.isEmpty()) {
            throw new Exception("CartItem not found with ID: " + cartItemId);
        }
    }


}
