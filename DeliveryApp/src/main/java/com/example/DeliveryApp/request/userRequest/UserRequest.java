package com.example.DeliveryApp.request.userRequest;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
