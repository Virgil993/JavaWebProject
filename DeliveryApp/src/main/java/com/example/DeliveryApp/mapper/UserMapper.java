package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.request.userRequest.UserRequest;
import com.example.DeliveryApp.response.userResponse.LoginUserResponse;
import com.example.DeliveryApp.response.userResponse.UserResponse;

import java.security.SecureRandom;
import java.util.Base64;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());

        return response;
    }


    public static User toEntity(UserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        return user;
    }

    public static LoginUserResponse toLoginUserResponse(User user) {

        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        String token = base64Encoder.encodeToString(randomBytes);

        LoginUserResponse response = new LoginUserResponse();
        response.setId(user.getId());
        response.setToken(token);
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}
