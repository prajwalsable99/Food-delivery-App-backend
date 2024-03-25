package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.request.CreateRestaurantRequest;
import com.prajwal.FoodApp.response.AuthResponse;
import com.prajwal.FoodApp.response.MessageResponse;
import com.prajwal.FoodApp.service.RestaurantService;
import com.prajwal.FoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurantHandler(@RequestBody CreateRestaurantRequest request , @RequestHeader("Authorization" )String token) throws Exception {
        User user=userService.findUserProfile(token);
        Restaurant restaurant=restaurantService.createRestaurant(request,user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurantHandler(@RequestBody CreateRestaurantRequest request , @RequestHeader("Authorization" )String token ,@PathVariable Long id) throws Exception {

        Restaurant restaurant=restaurantService.updateRestaurant(id,request);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurantHandler(@RequestHeader("Authorization" )String token, @PathVariable Long id) throws Exception {

        restaurantService.deleteRestaurant(id);

        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("deleted successfully");
        messageResponse.setFlag(true);


        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Restaurant> updateStatusRestaurantHandler(@PathVariable Long id) throws Exception {

        Restaurant restaurant=restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantHandler(@RequestHeader("Authorization" )String token ) throws Exception {
        User user=userService.findUserProfile(token);
        Restaurant restaurant=restaurantService.findRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


}
