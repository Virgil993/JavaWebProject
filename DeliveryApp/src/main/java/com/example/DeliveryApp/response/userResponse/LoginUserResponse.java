package com.example.DeliveryApp.response.userResponse;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginUserResponse {

    private Long id;
    private String token;
    private String email;
    private String name;
}
