package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByCustomer_Id(Long id);
}
