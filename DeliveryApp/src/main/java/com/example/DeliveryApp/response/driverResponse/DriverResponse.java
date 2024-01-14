package com.example.DeliveryApp.response.driverResponse;

import lombok.Data;

import java.util.UUID;

@Data
public class DriverResponse {
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String vehicle;
    private Integer age;
    private String deliveryExperience;
}