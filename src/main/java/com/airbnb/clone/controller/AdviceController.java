package com.airbnb.clone.controller;

import com.airbnb.clone.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    //**
    //Send exception
    @ExceptionHandler(AppException.class)
    public ResponseEntity<String> handleBookingException(AppException e) {
        return new ResponseEntity<>(e.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
