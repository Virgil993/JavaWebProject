package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.request.userRequest.LoginUserRequest;
import com.example.DeliveryApp.request.userRequest.UserRequest;
import com.example.DeliveryApp.response.userResponse.LoginUserResponse;
import com.example.DeliveryApp.response.userResponse.UserResponse;
import com.example.DeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
}
