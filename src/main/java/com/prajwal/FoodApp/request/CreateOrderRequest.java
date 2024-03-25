package com.prajwal.FoodApp.request;

import com.prajwal.FoodApp.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private Long restaurantId;

    private Long addressId;

    private  String orderStatus;

    private String transactionId;
}
