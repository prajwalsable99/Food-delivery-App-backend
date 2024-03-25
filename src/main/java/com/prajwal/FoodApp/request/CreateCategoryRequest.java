package com.prajwal.FoodApp.request;

import lombok.Data;

@Data
public class CreateCategoryRequest {


        private String name;
        private Long restaurantId;
}
