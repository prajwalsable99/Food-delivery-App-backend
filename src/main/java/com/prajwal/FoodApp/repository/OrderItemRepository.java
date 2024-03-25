package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
