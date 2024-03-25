package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.*;
import com.prajwal.FoodApp.repository.AddressRepository;
import com.prajwal.FoodApp.repository.OrderRepository;
import com.prajwal.FoodApp.request.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Override
    public Order createOrder(CreateOrderRequest request,String token) throws Exception {
//      1.user
        User user=userService.findUserProfile(token);

        Optional<Address> isAddress=addressRepository.findById(request.getAddressId());
        if(isAddress.isEmpty()){
            throw new Exception("Address invalid");
        }
//        2.address
        Address address=isAddress.get();

//        3.restaurant
        Restaurant restaurant=restaurantService.findRestaurantById(request.getRestaurantId());

        Order order=new Order();
        order.setCustomer(user);
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(address);
        order.setOrderStatus(request.getOrderStatus());
        order.setCreatedAt(new Date());
        order.setTransactionId(request.getTransactionId());

        Cart cart=cartService.getCart(user.getId());
        if(cart.getItems().isEmpty()){

            throw new Exception("Cart is empty");

        }
        List<OrderItem> orderItems=new ArrayList<>();

        for(CartItem cartItem:cart.getItems() ){

            OrderItem orderItem=new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            orderItems.add(orderItem);

        }

        order.setItems(orderItems);

        order.setTotalAmount(cart.getTotal());



        cartService.clearCart(user.getId());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
       return orderRepository.findByCustomer_Id(userId);
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId) {
        return orderRepository.findByRestaurant_Id(restaurantId);
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) return order.get();

        throw new Exception("Order not found with the id "+orderId);
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order=findOrderById(orderId);



        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);

            return orderRepository.save(order);
        }
        else throw new Exception("Please Select A Valid Order Status");


    }

}
