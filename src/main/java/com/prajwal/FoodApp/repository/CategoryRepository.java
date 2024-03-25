package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByNameAndRestaurantId(String name, Long restaurantId);
    List<Category> findByRestaurant_Id(Long restaurantId);
}
