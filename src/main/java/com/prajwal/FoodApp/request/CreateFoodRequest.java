package com.prajwal.FoodApp.request;

import com.prajwal.FoodApp.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private String categoryName;
    private Long price;
    private List<String> images;
    private  boolean veg;
    private boolean seasonal;
    private boolean available;

    private Long restaurantId;



}
