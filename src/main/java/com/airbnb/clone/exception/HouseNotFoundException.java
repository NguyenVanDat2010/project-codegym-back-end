package com.airbnb.clone.exception;

public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(String message){
        super(message);
    }
}
