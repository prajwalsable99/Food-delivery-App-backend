package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Order;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.request.UpdateOrderRequest;
import com.prajwal.FoodApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/restaurant/{id}")
    ResponseEntity<List<Order>> getRestaurantsOrders(@PathVariable Long id) throws Exception {

        List<Order> orders=orderService.getRestaurantOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<List<Order>> getUsersOrders(@PathVariable Long id) throws Exception {

        List<Order> orders=orderService.getUserOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<Order> updateOrder(@RequestBody UpdateOrderRequest request ) throws Exception{

        Order order=orderService.updateOrder(request.getOrderId(),request.getStatus());
        return new ResponseEntity<>(order,HttpStatus.OK);
    }



}
