package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Category;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        if(categoryRepository.findByNameAndRestaurantId(name,restaurantId).isPresent()){
            throw new Exception("category already exits in restaurant =>"+name);
        }

        Category category=new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);

    }

    @Override
    public Category findCategoryById(Long id) throws Exception {

        Optional<Category> opt=categoryRepository.findById(id);

        if(opt.isEmpty()) {
            throw new Exception("category not exist with id "+id);
        }

        return opt.get();
    }

    @Override
    public List<Category> findCategoriesByRestaurantId(Long restaurantId) {
        return categoryRepository.findByRestaurant_Id(restaurantId);
    }

    @Override
    public Boolean isCategoryAlreadyInRestaurant(String name, Long restaurantId) {
        return categoryRepository.findByNameAndRestaurantId(name, restaurantId).isPresent();
    }

    public Optional<Category> getCategoryByNameAndRestaurantId(String name, Long restaurantId) {
        return categoryRepository.findByNameAndRestaurantId(name, restaurantId);
    }
}
