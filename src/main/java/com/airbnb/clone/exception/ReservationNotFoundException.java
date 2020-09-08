package com.airbnb.clone.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(String s) {
        super(s);
    }
}
