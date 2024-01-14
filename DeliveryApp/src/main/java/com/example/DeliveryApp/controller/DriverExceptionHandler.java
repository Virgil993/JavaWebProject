package com.example.DeliveryApp.controller;
import com.example.DeliveryApp.dto.ErrorDTO;
import com.example.DeliveryApp.exception.driverException.DriverAlreadyExistsException;
import com.example.DeliveryApp.exception.driverException.DriverDoesNotExistException;
import com.example.DeliveryApp.exception.driverException.DriverValidationException;
import com.example.DeliveryApp.exception.driverException.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class DriverExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final DriverValidationException exception) {
        final var response = new ErrorDTO(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final DriverAlreadyExistsException exception) {
        final var response = new ErrorDTO(CONFLICT.value(), CONFLICT.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(CONFLICT).body(response);
    }



    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final DriverDoesNotExistException exception) {
        final var response = new ErrorDTO(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final WrongCredentialsException exception) {
        final var response = new ErrorDTO(UNAUTHORIZED.value(), UNAUTHORIZED.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(response);
    }

}