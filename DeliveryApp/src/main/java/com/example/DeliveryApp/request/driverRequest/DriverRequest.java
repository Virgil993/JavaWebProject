package com.example.DeliveryApp.request.driverRequest;

import lombok.Data;

@Data
public class DriverRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String vehicle;
    private Integer age;
    private String deliveryExperience;
}
