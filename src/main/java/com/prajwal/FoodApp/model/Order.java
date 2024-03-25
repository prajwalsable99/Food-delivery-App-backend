package com.prajwal.FoodApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private Long totalAmount;

    private String orderStatus ;

    private Date createdAt;

    private String transactionId;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> items;





}
