package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Category;
import com.prajwal.FoodApp.model.Food;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.repository.FoodRepository;
import com.prajwal.FoodApp.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req) throws Exception {

        Optional<Category> isCategory=categoryService.getCategoryByNameAndRestaurantId(req.getCategoryName(), req.getRestaurantId());

        if(isCategory.isEmpty()){
                throw new Exception("Food category not available in restaurant,first add category then come");
        }

        Category category=isCategory.get();
        Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());

        Food food=new Food();

        food.setName(req.getName());
        food.setDescription(req.getDescription());
        food.setPrice(req.getPrice());
        food.setImages(req.getImages());
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setVegetarian(req.isVeg());
        food.setSeasonal(req.isSeasonal());
        food.setAvailable(req.isAvailable());
        food.setCreationDate(new Date());
        Food savedfood=foodRepository.save(food);
        restaurant.getFoods().add(savedfood);

        return savedfood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food=findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.delete(food);

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVeg, boolean isNonveg, boolean isSeasonal) {
        List<Food> foods=foodRepository.findByRestaurantId(restaurantId);

        if (isVeg) {
            foods = filterByVegetarian(foods, true);
        }
        if (isNonveg) {
            foods = filterByNonveg(foods, true);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods, true);
        }

        return foods;

    }

    @Override
    public List<Food> filterByFoodCategory(Long restaurantId, String foodCategory) {
        List<Food> foods=foodRepository.findByRestaurantId(restaurantId);

        return foods.stream()
                .filter(food -> {
                    if (food.getFoodCategory() != null) {
                        return food.getFoodCategory().getName().equals(foodCategory);
                    }
                    return false; // Return true if food category is null
                })
                .collect(Collectors.toList());

    }


    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == isVegetarian)
                .collect(Collectors.toList());
    }
    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream()
                .filter(food -> !food.isVegetarian())
                .collect(Collectors.toList());
    }
    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream()
                .filter(food -> food.isSeasonal() == isSeasonal)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFoods(String query) {


        return foodRepository.searchFood(query);
    }

    @Override
    public Food findFoodById(Long FoodId) throws Exception {
        Optional<Food> food=foodRepository.findById(FoodId);
        if(food.isEmpty()){
            throw new Exception("food not found");
        }
        return food.get();
    }

    @Override
    public Food updateAvailabilityOfFood(Long FoodId) throws Exception {
       Food food=findFoodById(FoodId);
       food.setAvailable(!food.isAvailable());
       return foodRepository.save(food);

    }
}
