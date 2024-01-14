package com.example.DeliveryApp.request.restaurantRequest;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantRequest {
    private String name;
    private String email;
    private String password;
    private Integer priceRating;
    private Integer rating;
    private List<String> foodTypes;
    private List<String> supportPhoneNumbers;
}
