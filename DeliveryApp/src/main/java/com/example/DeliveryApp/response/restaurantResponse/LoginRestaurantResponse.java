package com.example.DeliveryApp.response.restaurantResponse;

import lombok.Data;

import java.util.List;

@Data
public class LoginRestaurantResponse {
    private Long id;

    private String token;
    private String name;
    private String email;

}
