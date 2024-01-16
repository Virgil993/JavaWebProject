package com.example.DeliveryApp.service;

import com.example.DeliveryApp.entity.Driver;
import com.example.DeliveryApp.entity.Restaurant;
import com.example.DeliveryApp.exception.restaurantException.RestaurantAlreadyExistsException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantDoesNotExistException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantValidationException;
import com.example.DeliveryApp.exception.restaurantException.WrongCredentialsException;
import com.example.DeliveryApp.mapper.RestaurantMapper;
import com.example.DeliveryApp.repository.DriverRepository;
import com.example.DeliveryApp.repository.RestaurantRepository;
import com.example.DeliveryApp.request.restaurantRequest.RestaurantRequest;
import com.example.DeliveryApp.request.restaurantRequest.LoginRestaurantRequest;
import com.example.DeliveryApp.response.restaurantResponse.RestaurantResponse;
import com.example.DeliveryApp.response.restaurantResponse.LoginRestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DriverRepository driverRepository;

    public RestaurantResponse register(RestaurantRequest request){
        if(request.getEmail().isBlank() || request.getPassword().isBlank() || request.getName().isBlank()){
            throw new RestaurantValidationException("Email, password, name, are all mandatory fields");
        }
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        if(request.getSupportPhoneNumbers()!=null){
            for (var i=0;i < request.getSupportPhoneNumbers().size();i++){
                if(!request.getSupportPhoneNumbers().get(i).matches(pattern)){
                    throw new RestaurantValidationException("Phone number "+request.getSupportPhoneNumbers().get(i)+" is not valid");
                }
            }
        }
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByEmail(request.getEmail());
        if(restaurantOptional.isPresent()){
            throw new RestaurantAlreadyExistsException("Restaurant already exists with this email");
        }
        Restaurant restaurant = RestaurantMapper.toEntity(request);
        List<Driver> drivers = driverRepository.findAll();
        for(var i=0;i<drivers.size();i++){
            drivers.get(i).getRestaurants().add(restaurant);
        }
        restaurant = restaurantRepository.save(restaurant);
        return RestaurantMapper.toResponse(restaurant);
    }

    public LoginRestaurantResponse login(LoginRestaurantRequest request){
        if(request.email() == null || request.password() == null ){
            throw new RestaurantValidationException("Password and email must not be empty");
        }
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByEmail(request.email());
        if(restaurantOptional.isEmpty()){
            throw new RestaurantDoesNotExistException("Restaurant doesn't exist with this email");
        }
        final var restaurant=restaurantOptional.get();

        if(!restaurant.getPassword().equals(request.password())){
            throw new WrongCredentialsException("Wrong credentials");
        }
        return RestaurantMapper.toLoginRestaurantResponse( restaurant);
    }

    public List<RestaurantResponse> getRestaurants(){
            List<Restaurant> restaurantsEntities = restaurantRepository.findAll();
            List<RestaurantResponse> response = new ArrayList<>();
            for(var i=0;i<restaurantsEntities.size();i++){
                response.add(RestaurantMapper.toResponse(restaurantsEntities.get(i)));
            }
            return response;
    }
}
