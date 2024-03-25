package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Food;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.request.CreateFoodRequest;
import com.prajwal.FoodApp.response.MessageResponse;
import com.prajwal.FoodApp.service.FoodService;
import com.prajwal.FoodApp.service.RestaurantService;
import com.prajwal.FoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Food> createFoodHandler(@RequestBody CreateFoodRequest request, @RequestHeader("Authorization") String token) throws Exception {

        Restaurant restaurant=restaurantService.findRestaurantById(request.getRestaurantId());

       Food food=foodService.createFood(request);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id) throws Exception {

        foodService.deleteFood(id);

        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setFlag(true);
        messageResponse.setMessage("delete food success");
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }


    @PutMapping("/available/{id}")
    public ResponseEntity<Food> availUpdateFoodHandler(@PathVariable Long id) throws Exception {


        Food food=foodService.updateAvailabilityOfFood(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}
