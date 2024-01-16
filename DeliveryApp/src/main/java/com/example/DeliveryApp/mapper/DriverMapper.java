package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.Driver;
import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.exception.driverException.WrongCredentialsException;
import com.example.DeliveryApp.request.driverRequest.DriverRequest;
import com.example.DeliveryApp.request.driverRequest.LoginDriverRequest;
import com.example.DeliveryApp.request.userRequest.UserRequest;
import com.example.DeliveryApp.response.driverResponse.DriverResponse;
import com.example.DeliveryApp.response.driverResponse.LoginDriverResponse;
import com.example.DeliveryApp.response.userResponse.UserResponse;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class DriverMapper {

    public static DriverResponse toResponse(Driver driver) {
        DriverResponse response = new DriverResponse();

        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setEmail(driver.getEmail());
        response.setPhoneNumber(driver.getPhoneNumber());
        response.setAge(driver.getAge());
        response.setVehicle(driver.getVehicle());
        response.setDeliveryExperience(driver.getDeliveryExperience());

        return response;
    }


    public static Driver toEntity(DriverRequest request) {
        Driver driver = new Driver();

        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPassword(request.getPassword());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setAge(request.getAge());
        driver.setVehicle(request.getVehicle());
        driver.setDeliveryExperience(request.getDeliveryExperience());


        return driver;
    }

    public static LoginDriverResponse toLoginDriverResponse( Driver driver) {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);

        LoginDriverResponse response = new LoginDriverResponse();
        response.setId(driver.getId());
        response.setToken(token);
        response.setName(driver.getName());
        response.setEmail(driver.getEmail());
        return response;
    }
}
