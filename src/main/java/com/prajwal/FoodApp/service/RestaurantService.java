package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.dto.RestaurantDto;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception;
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest req) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();
    public List<Restaurant> searchRestaurants(String query);

    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant findRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addtoFavorites(Long restaurantID,User user)throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;


}
