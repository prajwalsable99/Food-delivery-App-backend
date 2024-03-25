package com.prajwal.FoodApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Category foodCategory;

    private Long price;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    @JsonIgnoreProperties({"owner", "description", "cuisineType", "address", "contactInformation", "openingHours", "images", "registrationDate"})
    private  Restaurant restaurant;

    private  boolean isVegetarian;

    private boolean isSeasonal;



    private Date creationDate;

}
