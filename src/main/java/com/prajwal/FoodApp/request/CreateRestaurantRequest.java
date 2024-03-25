package com.prajwal.FoodApp.request;

import com.prajwal.FoodApp.model.Address;
import com.prajwal.FoodApp.model.ContactInformation;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
//    private LocalDateTime registrationDate;
    private boolean open;


}
