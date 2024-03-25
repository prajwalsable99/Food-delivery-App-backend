package com.prajwal.FoodApp.repository;

import com.prajwal.FoodApp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant findByOwnerId(Long userId);

    @Query("select r from Restaurant r where lower(r.name) LIKE  lower(concat('%',:query,'%')) or  lower(r.cuisineType) LIKE  lower(concat('%',:query,'%')) ")
    List<Restaurant> searchRestaurants(String query);
}
