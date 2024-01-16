package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.Restaurant;
import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.request.restaurantRequest.RestaurantRequest;
import com.example.DeliveryApp.response.restaurantResponse.LoginRestaurantResponse;
import com.example.DeliveryApp.response.restaurantResponse.RestaurantResponse;
import com.example.DeliveryApp.response.userResponse.LoginUserResponse;

import java.security.SecureRandom;
import java.util.Base64;

public class RestaurantMapper {

    public static RestaurantResponse toResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();

        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setEmail(restaurant.getEmail());
        response.setRating(restaurant.getRating());
        response.setFoodTypes(restaurant.getFoodTypes());
        response.setPriceRating(restaurant.getPriceRating());
        response.setSupportPhoneNumbers(restaurant.getSupportPhoneNumbers());

        return response;
    }


    public static Restaurant toEntity(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(request.getName());
        restaurant.setEmail(request.getEmail());
        restaurant.setPassword(request.getPassword());
        restaurant.setRating(request.getRating());
        restaurant.setFoodTypes(request.getFoodTypes());
        restaurant.setPriceRating(request.getPriceRating());
        restaurant.setSupportPhoneNumbers(request.getSupportPhoneNumbers());


        return restaurant;
    }

    public static LoginRestaurantResponse toLoginRestaurantResponse(Restaurant restaurant) {

        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);

        LoginRestaurantResponse response = new LoginRestaurantResponse();
        response.setId(restaurant.getId());
        response.setToken(token);
        response.setName(restaurant.getName());
        response.setEmail(restaurant.getEmail());
        return response;
    }
}