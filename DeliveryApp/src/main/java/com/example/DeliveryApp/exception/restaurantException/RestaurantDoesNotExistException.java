package com.example.DeliveryApp.exception.restaurantException;

public class RestaurantDoesNotExistException extends RuntimeException{
    public RestaurantDoesNotExistException(final String message){super(message);}
}
