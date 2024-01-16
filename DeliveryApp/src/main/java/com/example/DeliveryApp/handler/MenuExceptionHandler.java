package com.example.DeliveryApp.handler;

import com.example.DeliveryApp.dto.ErrorDTO;
import com.example.DeliveryApp.exception.driverException.DriverValidationException;
import com.example.DeliveryApp.exception.menuException.MenuDoesNotExistException;
import com.example.DeliveryApp.exception.menuException.MenuValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class MenuExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final MenuValidationException exception) {
        final var response = new ErrorDTO(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(final MenuDoesNotExistException exception) {
        final var response = new ErrorDTO(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(response);
    }
}
