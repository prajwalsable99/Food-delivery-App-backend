package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Cart;
import com.prajwal.FoodApp.model.CartItem;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.response.MessageResponse;
import com.prajwal.FoodApp.service.CartService;
import com.prajwal.FoodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;


    @GetMapping("/my")
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String token) throws Exception {
        User user=userService.findUserProfile(token);
        Cart cart=cartService.getCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }

    @PutMapping("/add/food/{id}")
    public ResponseEntity<CartItem> addToCart(@RequestHeader("Authorization") String token, @PathVariable Long id) throws Exception {
        User user=userService.findUserProfile(token);
        CartItem cartItem=cartService.addItem(id,user.getId());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);

    }

    @DeleteMapping("/remove/food/{id}")
    public ResponseEntity<MessageResponse> removeFromCart(@RequestHeader("Authorization") String token, @PathVariable Long id) throws Exception {
        User user=userService.findUserProfile(token);
        cartService.removeItem(id,user.getId());
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setFlag(true);
        messageResponse.setMessage("Deleted item successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

   @DeleteMapping("/remove/cartitem/{id}")
   public ResponseEntity<MessageResponse> removeFromCartByCartItemId(@RequestHeader("Authorization") String token, @PathVariable Long id) throws Exception {
       User user=userService.findUserProfile(token);
       cartService.removeCartItemById(id,user.getId());
       MessageResponse messageResponse=new MessageResponse();
       messageResponse.setFlag(true);
       messageResponse.setMessage("Deleted item successfully");
       return new ResponseEntity<>(messageResponse, HttpStatus.OK);

   }

    @DeleteMapping("/clear")
    public ResponseEntity<MessageResponse> removeFromCartByCartItemId(@RequestHeader("Authorization") String token) throws Exception {
        User user=userService.findUserProfile(token);
        cartService.clearCart(user.getId());
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setFlag(true);
        messageResponse.setMessage("cart cleared successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }




}
