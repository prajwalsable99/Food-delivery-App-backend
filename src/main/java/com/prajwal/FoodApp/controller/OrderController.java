package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Order;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.request.CreateOrderRequest;
import com.prajwal.FoodApp.response.MessageResponse;
import com.prajwal.FoodApp.service.OrderService;
import com.prajwal.FoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    ResponseEntity<Order> createOrderHandler(@RequestBody CreateOrderRequest request, @RequestHeader("Authorization") String token) throws Exception {

//        MessageResponse messageResponse=new MessageResponse();
//        String message =request.toString();
//        messageResponse.setFlag(true);
//        messageResponse.setMessage(message);
//        return new ResponseEntity<>(messageResponse,HttpStatus.OK);


        Order order=orderService.createOrder(request,token);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    ResponseEntity<List<Order>> getMyOrders(@RequestHeader("Authorization") String token) throws Exception {
        User user=userService.findUserProfile(token);
        List<Order> orders=orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

}
