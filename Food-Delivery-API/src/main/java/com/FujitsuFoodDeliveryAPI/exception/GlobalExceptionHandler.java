package com.FujitsuFoodDeliveryAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectTimeStampException.class)
    public ResponseEntity<Object> handleIncorrectTimestampException(IncorrectTimeStampException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidVehicleException.class)
    public ResponseEntity<Object> handleInvalidVehicleException(InvalidVehicleException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCityException.class)
    public ResponseEntity<Object> handleInvalidCityException(InvalidCityException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        // Log the exception details for debugging
        // Return a user-friendly message and a Bad Request status
        return new ResponseEntity<>(
                "The parameter '" + name + "' is required.",
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("The parameter '%s' requires a value of type '%s', but '%s' was provided.", name, type, value);

        // Log the exception details for debugging
        // Return a user-friendly message and a Bad Request status
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}

