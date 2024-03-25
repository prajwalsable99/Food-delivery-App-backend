package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.dto.RestaurantDto;
import com.prajwal.FoodApp.model.Address;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.response.MessageResponse;
import com.prajwal.FoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<User> findUserProfile(@RequestHeader("Authorization") String token) throws Exception {

        User user=userService.findUserProfile(token);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/add/address")
    public ResponseEntity<MessageResponse> addAddress(@RequestHeader("Authorization") String token, @RequestBody Address address) throws Exception {

        Address address1=userService.AddUserAddress(token,address);

        MessageResponse messageResponse =new MessageResponse();
        messageResponse.setMessage("address added");
        messageResponse.setFlag(true);
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);

    }

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddress(@RequestHeader("Authorization") String token) throws Exception {

        List<Address> addresses=userService.getUserAddresses(token);
        return new ResponseEntity<>(addresses,HttpStatus.OK);

    }

    @GetMapping("/favs")
    public ResponseEntity<List<RestaurantDto>> getFvs(@RequestHeader("Authorization") String token) throws Exception {


        User user=  userService.findUserProfile(token);
        List<RestaurantDto> favs= user.getFavorites();
        return new ResponseEntity<>(favs,HttpStatus.OK);

    }


}
