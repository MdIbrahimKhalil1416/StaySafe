package com.example.stayease.exception;

/** Thrown when a booking is attempted against a room that isn't available. */
public class InvalidBookingException extends RuntimeException {
    public InvalidBookingException(String message) {
        super(message);
    }
}
