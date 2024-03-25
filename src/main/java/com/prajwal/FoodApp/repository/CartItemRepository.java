package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.Cart;
import com.prajwal.FoodApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Optional<CartItem> findByCartIdAndFoodId(Long cartId, Long foodId);
}
