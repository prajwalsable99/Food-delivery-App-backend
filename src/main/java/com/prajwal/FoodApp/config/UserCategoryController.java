package com.prajwal.FoodApp.config;

import com.prajwal.FoodApp.model.Category;
import com.prajwal.FoodApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class UserCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Category>> getAll(@PathVariable Long id){

        List<Category>categories=categoryService.findCategoriesByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
