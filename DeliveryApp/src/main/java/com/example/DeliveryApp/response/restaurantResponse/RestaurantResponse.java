package com.example.DeliveryApp.response.restaurantResponse;

import lombok.Data;

import java.util.List;
@Data
public class RestaurantResponse {

    private Long id;

    private String name;
    private String email;
    private Integer priceRating;
    private Integer rating;
    private List<String> foodTypes;
    private List<String> supportPhoneNumbers;
}
