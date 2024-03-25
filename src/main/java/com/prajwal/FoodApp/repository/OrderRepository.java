package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order,Long> {

    List<Order> findByCustomer_Id(Long userId);
    List<Order> findByRestaurant_Id(Long restaurantId);
}
