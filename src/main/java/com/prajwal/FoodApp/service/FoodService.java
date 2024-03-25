package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Category;
import com.prajwal.FoodApp.model.Food;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req ) throws Exception;

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,boolean isVeg,boolean isNonVeg,boolean isSeasonal);

    public List<Food> searchFoods(String query);

    public  List<Food> filterByFoodCategory(Long restaurantId, String foodCategory);


    public Food findFoodById(Long FoodId) throws Exception;
    public Food updateAvailabilityOfFood(Long FoodId) throws Exception;


}
