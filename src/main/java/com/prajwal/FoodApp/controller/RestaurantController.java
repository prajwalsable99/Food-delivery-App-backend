package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.dto.RestaurantDto;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.service.RestaurantService;
import com.prajwal.FoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurantHandler(@RequestParam String query) throws Exception {

        List<Restaurant> restaurants=restaurantService.searchRestaurants(query);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getRestaurantHandler() throws Exception {

        List<Restaurant> restaurants=restaurantService.getAllRestaurants();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Restaurant> getByIDRestaurantHandler(@PathVariable Long id) throws Exception {

        Restaurant restaurant=restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/fav/{id}")
    public ResponseEntity<RestaurantDto> addFavRestaurantHandler(@PathVariable Long id , @RequestHeader("Authorization" )String token) throws Exception {
        User user=userService.findUserProfile(token);
        RestaurantDto restaurant=restaurantService.addtoFavorites(id,user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }





}
