package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Category createCategory(String name ,Long restaurantId) throws Exception;

    public Category findCategoryById(Long id) throws Exception;
    public List<Category> findCategoriesByRestaurantId(Long restaurantId);

    public Boolean isCategoryAlreadyInRestaurant(String name ,Long restaurantId);
    public Optional<Category> getCategoryByNameAndRestaurantId(String name, Long restaurantId);

}
