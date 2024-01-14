package com.example.DeliveryApp.response.driverResponse;

import lombok.Data;

@Data
public class LoginDriverResponse {

    private Long id;
    private String token;
    private String email;
    private String name;
}
