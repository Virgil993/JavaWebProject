package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.request.userRequest.UserRequest;
import com.example.DeliveryApp.response.userResponse.UserResponse;

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
}
