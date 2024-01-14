package com.example.DeliveryApp.service;

import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.exception.userException.UserAlreadyExistsException;
import com.example.DeliveryApp.exception.userException.UserDoesNotExistException;
import com.example.DeliveryApp.exception.userException.UserValidationException;
import com.example.DeliveryApp.exception.userException.WrongCredentialsException;
import com.example.DeliveryApp.mapper.UserMapper;
import com.example.DeliveryApp.repository.UserRepository;
import com.example.DeliveryApp.request.userRequest.LoginUserRequest;
import com.example.DeliveryApp.request.userRequest.UserRequest;
import com.example.DeliveryApp.response.userResponse.LoginUserResponse;
import com.example.DeliveryApp.response.userResponse.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse register(UserRequest request){
        if(request.getEmail().isBlank() || request.getPassword().isBlank() || request.getName().isBlank() || request.getPhoneNumber().isBlank() ){
            throw new UserValidationException("All fields are mandatory");
        }
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        if(!request.getPhoneNumber().matches(pattern)){
            throw new UserValidationException("Phone number is not valid");
        }
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isPresent()){
            throw new UserAlreadyExistsException("User already exists with this email");
        }
        User user = UserMapper.toEntity(request);
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    public LoginUserResponse login(LoginUserRequest request){
        if(request.email() == null || request.password() == null ){
            throw new UserValidationException("Password and email must not be empty");
        }
        Optional<User> userOptional = userRepository.findByEmail(request.email());
        if(userOptional.isEmpty()){
            throw new UserDoesNotExistException("User doesn't exist with this email");
        }
        final var user=userOptional.get();

        return getLoginUserResponse(request, user);
    }

    private static LoginUserResponse getLoginUserResponse(LoginUserRequest request, User user) {
        if(!user.getPassword().equals(request.password())){
            throw new WrongCredentialsException("Wrong credentials");
        }
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
