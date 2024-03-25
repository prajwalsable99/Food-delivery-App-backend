package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.model.Category;
import com.prajwal.FoodApp.request.CreateCategoryRequest;
import com.prajwal.FoodApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest request) throws Exception {

        Category category=categoryService.createCategory(request.getName(),request.getRestaurantId());

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Category>> getAll(@PathVariable Long id){

        List<Category>categories=categoryService.findCategoriesByRestaurantId(id);
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id) throws Exception {

        Category category=categoryService.findCategoryById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

}
