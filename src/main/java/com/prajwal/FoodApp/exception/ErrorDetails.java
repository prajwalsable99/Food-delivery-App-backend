package com.prajwal.FoodApp.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorDetails {
    private String message;
    private String details;

    private LocalDateTime timestamp;

    public  ErrorDetails(){}

    public ErrorDetails(String message, String details, LocalDateTime timestamp) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

}