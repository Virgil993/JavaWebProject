package com.example.DeliveryApp.response.userResponse;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
}
