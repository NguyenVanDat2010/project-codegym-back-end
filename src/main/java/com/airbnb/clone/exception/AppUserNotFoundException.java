package com.airbnb.clone.exception;

public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException(String message) {
        super(message);
    }
}
