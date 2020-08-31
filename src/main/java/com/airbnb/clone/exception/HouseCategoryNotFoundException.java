package com.airbnb.clone.exception;

public class HouseCategoryNotFoundException extends RuntimeException{
    public HouseCategoryNotFoundException(String message){
        super(message);
    }
}
