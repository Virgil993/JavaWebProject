package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.dto.ErrorDTO;
import com.example.DeliveryApp.exception.userException.UserAlreadyExistsException;
import com.example.DeliveryApp.exception.userException.UserDoesNotExistException;
import com.example.DeliveryApp.exception.userException.UserValidationException;
import com.example.DeliveryApp.exception.userException.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final UserValidationException exception) {
        final var response = new ErrorDTO(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final UserAlreadyExistsException exception) {
        final var response = new ErrorDTO(CONFLICT.value(), CONFLICT.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(CONFLICT).body(response);
    }



    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final UserDoesNotExistException exception) {
        final var response = new ErrorDTO(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final WrongCredentialsException exception) {
        final var response = new ErrorDTO(UNAUTHORIZED.value(), UNAUTHORIZED.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(response);
    }

}
