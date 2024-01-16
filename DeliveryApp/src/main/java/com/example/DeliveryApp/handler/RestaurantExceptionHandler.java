package com.example.DeliveryApp.handler;

import com.example.DeliveryApp.dto.ErrorDTO;
import com.example.DeliveryApp.exception.restaurantException.RestaurantAlreadyExistsException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantDoesNotExistException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantValidationException;
import com.example.DeliveryApp.exception.restaurantException.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class RestaurantExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final RestaurantValidationException exception) {
        final var response = new ErrorDTO(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final RestaurantAlreadyExistsException exception) {
        final var response = new ErrorDTO(CONFLICT.value(), CONFLICT.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(CONFLICT).body(response);
    }



    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final RestaurantDoesNotExistException exception) {
        final var response = new ErrorDTO(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final WrongCredentialsException exception) {
        final var response = new ErrorDTO(UNAUTHORIZED.value(), UNAUTHORIZED.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(response);
    }
}
