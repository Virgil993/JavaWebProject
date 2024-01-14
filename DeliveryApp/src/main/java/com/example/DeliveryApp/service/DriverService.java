package com.example.DeliveryApp.service;


import com.example.DeliveryApp.entity.Driver;
import com.example.DeliveryApp.exception.driverException.DriverAlreadyExistsException;
import com.example.DeliveryApp.exception.driverException.DriverDoesNotExistException;
import com.example.DeliveryApp.exception.driverException.DriverValidationException;
import com.example.DeliveryApp.exception.driverException.WrongCredentialsException;
import com.example.DeliveryApp.mapper.DriverMapper;
import com.example.DeliveryApp.repository.DriverRepository;
import com.example.DeliveryApp.request.driverRequest.DriverRequest;
import com.example.DeliveryApp.request.driverRequest.LoginDriverRequest;
import com.example.DeliveryApp.response.driverResponse.DriverResponse;
import com.example.DeliveryApp.response.driverResponse.LoginDriverResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public DriverResponse register(DriverRequest request){
        if(request.getEmail().isBlank() || request.getPassword().isBlank() || request.getAge() == null || request.getName().isBlank() || request.getVehicle().isBlank() || request.getPhoneNumber().isBlank()){
            throw new DriverValidationException("Email, password, age, name, vehicle, phoneNumber are all mandatory fields");
        }
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        if(!request.getPhoneNumber().matches(pattern)){
            throw new DriverValidationException("Phone number is not valid");
        }
        Optional<Driver> driverOptional = driverRepository.findByEmail(request.getEmail());
        if(driverOptional.isPresent()){
            throw new DriverAlreadyExistsException("Driver already exists with this email");
        }
        Driver driver = DriverMapper.toEntity(request);
        driver = driverRepository.save(driver);
        return DriverMapper.toResponse(driver);
    }

    public LoginDriverResponse login(LoginDriverRequest request){
        if(request.email() == null || request.password() == null ){
            throw new DriverValidationException("Password and email must not be empty");
        }
        Optional<Driver> driverOptional = driverRepository.findByEmail(request.email());
        if(driverOptional.isEmpty()){
            throw new DriverDoesNotExistException("Driver doesn't exist with this email");
        }
        final var driver=driverOptional.get();

        return getLoginDriverResponse(request, driver);
    }

    private static LoginDriverResponse getLoginDriverResponse(LoginDriverRequest request, Driver driver) {
        if(!driver.getPassword().equals(request.password())){
            throw new WrongCredentialsException("Wrong credentials");
        }
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