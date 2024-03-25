package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Food;
import com.prajwal.FoodApp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    FoodService foodService;
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String query){

        List<Food>foods=foodService.searchFoods(query);

        return new ResponseEntity<>(foods, HttpStatus.OK);

    }

    @GetMapping("/restaurant/all/{id}")
    public ResponseEntity<List<Food>> allFood(@PathVariable Long id,@RequestParam boolean isveg,@RequestParam boolean isnonveg,@RequestParam boolean isseasonal
    ){

        List<Food>foods=foodService.getRestaurantsFood(id,isveg,isnonveg,isseasonal );

        return new ResponseEntity<>(foods, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}/food/category/{category}")
    public ResponseEntity<List<Food>> allFood(@PathVariable Long id,@PathVariable String category){
        List<Food>foods=foodService.filterByFoodCategory(id, category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
