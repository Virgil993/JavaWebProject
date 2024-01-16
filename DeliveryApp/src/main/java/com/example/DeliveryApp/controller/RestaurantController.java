package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.exception.driverException.DriverValidationException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantValidationException;
import com.example.DeliveryApp.request.restaurantRequest.LoginRestaurantRequest;
import com.example.DeliveryApp.request.restaurantRequest.RestaurantDriverRequest;
import com.example.DeliveryApp.request.restaurantRequest.RestaurantRequest;
import com.example.DeliveryApp.response.restaurantResponse.LoginRestaurantResponse;
import com.example.DeliveryApp.response.restaurantResponse.RestaurantDriverResponse;
import com.example.DeliveryApp.response.restaurantResponse.RestaurantResponse;
import com.example.DeliveryApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("register")
    public ResponseEntity<RestaurantResponse> register( @RequestBody RestaurantRequest request){
        return ResponseEntity.ok(restaurantService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<LoginRestaurantResponse> login(@RequestBody LoginRestaurantRequest request){
        return ResponseEntity.ok(restaurantService.login(request));
    }

    @GetMapping()
    public ResponseEntity<List<RestaurantResponse>> getRestaurants(){
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @DeleteMapping("driver/association")
    public ResponseEntity<RestaurantDriverResponse> removeDriverFromRestaurant(@RequestBody RestaurantDriverRequest request){
        return ResponseEntity.ok(restaurantService.removeDriverFromRestaurant(request));
    }

    @PostMapping("driver/association")
    public ResponseEntity<RestaurantDriverResponse> addDriverToRestaurant(@RequestBody RestaurantDriverRequest request){
        return ResponseEntity.ok(restaurantService.addDriverToRestaurant(request));
    }
}