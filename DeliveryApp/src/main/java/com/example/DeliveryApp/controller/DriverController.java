package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.exception.driverException.DriverValidationException;
import com.example.DeliveryApp.request.driverRequest.LoginDriverRequest;
import com.example.DeliveryApp.request.driverRequest.DriverRequest;
import com.example.DeliveryApp.response.driverResponse.LoginDriverResponse;
import com.example.DeliveryApp.response.driverResponse.DriverResponse;
import com.example.DeliveryApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("register")
    public ResponseEntity<DriverResponse> register(@RequestBody DriverRequest request){
        return ResponseEntity.ok(driverService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<LoginDriverResponse> login(@RequestBody LoginDriverRequest request){
        return ResponseEntity.ok(driverService.login(request));
    }
}
