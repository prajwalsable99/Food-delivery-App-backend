package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Order;
import com.prajwal.FoodApp.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    Order createOrder(CreateOrderRequest request,String token) throws Exception;

    List<Order>  getUserOrders(Long userId);
    List<Order>  getRestaurantOrders(Long restaurantId);

    Order findOrderById(Long orderId) throws Exception;

    Order updateOrder(Long orderId, String orderStatus) throws Exception;
}
