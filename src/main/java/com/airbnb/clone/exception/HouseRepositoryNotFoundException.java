package com.airbnb.clone.exception;

public class HouseRepositoryNotFoundException extends RuntimeException{
    public HouseRepositoryNotFoundException(String message){
        super(message);
    }
}
